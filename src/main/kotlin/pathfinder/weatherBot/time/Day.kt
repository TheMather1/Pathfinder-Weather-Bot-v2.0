package pathfinder.weatherBot.time

import jakarta.persistence.*
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.weather.TemperatureRange
import pathfinder.weatherBot.weather.events.Event
import java.time.LocalDate

@Suppress("JpaObjectClassSignatureInspection")
@Entity(name = "DAYS")
class Day(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val date: LocalDate,
    @Embedded
    val temperatureRange: TemperatureRange,
    @OneToMany(cascade = [(CascadeType.ALL)], orphanRemoval = true)
    val hours: List<Hour>
) {

    val events: List<Event>
        get() = hours.flatMap { it.events }

    fun nextDay(config: GuildConfig) = fromConfig(config, date.plusDays(1), temperatureRange, hours.lastOrNull())

    companion object {
        fun fromConfig(config: GuildConfig, date: LocalDate = LocalDate.now(), prevRange: TemperatureRange? = null, prevHour: Hour? = null): Day {
            val season = Season(date)
            val temperatureRange = TemperatureRange(config, date, season, prevRange)
            return Day(
                date = date,
                temperatureRange = temperatureRange,
                hours = (0..23).fold(emptyList()) { o, i ->
                    o + Hour.create(
                        season,
                        date.atTime(i, 0),
                        temperatureRange,
                        o.lastOrNull() ?: prevHour,
                        config
                    )
                }
            )
        }
    }
}
