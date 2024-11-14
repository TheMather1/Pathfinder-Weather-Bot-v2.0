package pathfinder.weatherBot.interaction

import jakarta.persistence.*
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild
import org.hibernate.annotations.NaturalId
import pathfinder.weatherBot.time.Forecast
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

@Entity(name = "CLIENTS")
class Client(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @NaturalId
    val guildId: Long,
    @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, orphanRemoval = true)
    val config: GuildConfig,
    @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, orphanRemoval = true)
    var forecast: Forecast
) {

    private val now
        get() = ZonedDateTime.now(config.timezone).truncatedTo(ChronoUnit.HOURS)
    private val isMidnight
        get() = now.hour == 0
    val thisHour
        get() = if(isMidnight) forecast.tomorrow.hours[now.hour] else forecast.today.hours[now.hour]
    val lastHour
        get() = forecast.today.hours[if (isMidnight) 23 else now.hour - 1]

    fun status() = if (config.active) "running" else "stopped"

    fun resetForecast() = Forecast.fromConfig(config).also {
        forecast = it
    }

    fun reportWeather(jda: JDA, dryRun: Boolean = false) = thisHour.reportEmbed(lastHour)?.let {
        jda.getTextChannelById(config.outputChannel)!!.sendMessageEmbeds(it)
    } to if (isMidnight && !dryRun) {
        forecast.advanceDay(config)
        val prevEventIds = forecast.tomorrow.events.map { it.id }
        forecast.dayAfterTomorrow.events.any { it.id !in prevEventIds }
    } else false

    companion object {
        fun forGuild(guild: Guild): Client {
            val config = GuildConfig(outputChannel = guild.defaultChannel!!.idLong)
            return Client(0, guild.idLong, config, Forecast.fromConfig(config))
        }
    }
}
