package pathfinder.weatherBot.time

import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.weather.TemperatureRange
import java.io.Serializable
import java.time.LocalDate

class Day(config: GuildConfig, val date: LocalDate, prevDay: Day?) : Serializable {
    val season = Season(date)
    val temperatureRange: TemperatureRange = TemperatureRange(config, date, season, prevDay?.temperatureRange)
    val hours: Array<Hour?> = (0..23).fold(emptyArray()) { o, i ->
        val dateTime = date.atTime(i, 0)
        o + Hour(config, this, dateTime, o.lastOrNull() ?: prevDay?.hours?.lastOrNull())
    }

    fun nextDay(config: GuildConfig) = Day(config, date.plusDays(1), this)

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
}
