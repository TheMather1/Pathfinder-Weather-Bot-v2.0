package pathfinder.weatherBot.time

import jakarta.persistence.*
import jdk.jfr.Percentage
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.weather.Temperature
import pathfinder.weatherBot.weather.TemperatureRange
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.events.Event
import pathfinder.weatherBot.weather.precipitation.None
import java.time.LocalDateTime
import kotlin.math.pow

@Suppress("JpaObjectClassSignatureInspection")
@Entity(name = "HOURS")
class Hour(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val time: LocalDateTime,
    @Embedded
    var temp: Temperature,
    @Embedded
    val weather: Weather,
    @Percentage
    var humidity: Float = 1F,
    @ManyToMany(cascade = [CascadeType.ALL])
    val events: MutableList<Event> = mutableListOf()
) {

    val fireRisk: Float
        get() = (temp.temp - 100 - (weather.precipitation.type.fireRetardance)) * (1 - humidity).pow(2)

    private val humidMod
        get() = weather.precipitation.type.let {
            if(it !is None) it.fireRetardance * 0.01F
            else temp.evaporationMod
        }

    val nextHumidity
        get() = (humidity + humidMod).coerceIn(0F, 1F)

    private val warnings: List<String>
        get() = listOfNotNull(temp.warn(weather)).plus(weather.warn).plus(
            events.filter { it.active }.mapNotNull { it.type.warn }
        )

    val embed: MessageEmbed
        get() = EmbedBuilder()
            .setThumbnail(weather.iconUrl(time))
            .addField(temp.embedField(null))
            .apply {
                weather.embedFields(null).forEach {
                    addField(it)
                }
                events.filter { it.active && it.type.description != null }.forEach {
                    addField(it.name, it.type.description!!, false)
                }
            }
            .setFooter(warnings.joinToString("\n"))
            .build()


    fun addEvents(config: GuildConfig, prevHour: Hour?): Hour {
        events.addAll(
            Event.persistOrGenerateEvents(config, prevHour, this)
        )
        return this
    }

    private fun describeEvents(prevEvents: List<Event>) =
        listOfNotNull(
            events.filter { it.active }.mapNotNull { e ->
                e.type.describeChange(prevEvents)?.let { MessageEmbed.Field(e.name, it, false) }
            },
            prevEvents.filter { it.active && it.end == time }.map { e ->
                MessageEmbed.Field(e.name, e.type.finished(), false)
            }
        ).flatten()

    fun reportEmbed(prevHour: Hour?) = describeChange(
        prevHour?.temp,
        prevHour?.weather,
        prevHour?.events?.filter { it.active } ?: emptyList()
    )?.let { embedFields ->
        EmbedBuilder()
            .setThumbnail(weather.iconUrl(time))
            .apply {
                fields += embedFields
            }
            .setFooter(warnings.joinToString("\n"))
            .build()
    }

    private fun describeChange(prevTemp: Temperature?, prevWeather: Weather?, prevEvents: List<Event>) =
        (listOfNotNull(temp.embedField(prevTemp)) + weather.embedFields(prevWeather) + describeEvents(prevEvents)).takeUnless { it.isEmpty() }

    companion object {
        fun create(season: Season, time: LocalDateTime, tempRange: TemperatureRange, prevHour: Hour?, config: GuildConfig): Hour {
            val temp = tempRange.tempAtHour(time.toLocalTime())
            return Hour(
                time = time,
                temp = temp,
                weather = Weather(config, season, time, temp, prevHour?.weather),
                humidity = prevHour?.nextHumidity ?: 1F,
            ).addEvents(config, prevHour)
        }
    }
}
