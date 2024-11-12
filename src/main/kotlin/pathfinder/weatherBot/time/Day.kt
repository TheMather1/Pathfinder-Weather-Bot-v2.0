package pathfinder.weatherBot.time

import jakarta.persistence.CascadeType
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.weather.TemperatureRange
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.events.Event
import pathfinder.weatherBot.weather.events.tornado.Tornado
import pathfinder.weatherBot.weather.events.tornado.Tornado.Companion.invoke
import java.time.LocalDate

@Entity(name = "DAYS")
class Day(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val date: LocalDate,
    @Embedded
    val temperatureRange: TemperatureRange,
    @OneToMany(cascade = [(CascadeType.ALL)], orphanRemoval = true)
    val hours: Array<Hour>
) {
    @Transient
    val season = Season(date)

    val events: List<Event>
        get() = hours.flatMap { it.events }

    fun nextDay(config: GuildConfig) = fromConfig(config, date.plusDays(1), temperatureRange, hours.lastOrNull())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Day

        if (!hours.contentEquals(other.hours)) return false
        return date == other.date
    }

    override fun hashCode(): Int {
        var result = hours.contentHashCode()
        result = 31 * result + date.hashCode()
        return result
    }

    companion object {
        fun fromConfig(config: GuildConfig, date: LocalDate = LocalDate.now(), prevRange: TemperatureRange? = null, prevHour: Hour? = null): Day {
            val season = Season(date)
            val temperatureRange = TemperatureRange(config, date, season, config.climate, prevRange)
            return Day(
                date = date,
                temperatureRange = temperatureRange,
                hours = (0..23).fold(emptyArray()) { o, i ->
                    val dateTime = date.atTime(i, 0)
                    val temp = temperatureRange.tempAtHour(dateTime.toLocalTime())
                    val prevHour = o.lastOrNull() ?: prevHour
                    val events: MutableList<Event> = (prevHour?.events?.filter { it.end > dateTime }?.toMutableList() ?: mutableListOf())
                    val hour = Hour(
                        0,
                        dateTime,
                        temp,
                        Weather(config, season, dateTime, temp, prevHour?.weather),
                        prevHour?.nextHumidity ?: 1F,
                        events
                    )
                    if (events.none { it.type is Tornado }) Tornado(hour)?.let(events::add)
                    o + hour
                }
            )
        }
    }
}
