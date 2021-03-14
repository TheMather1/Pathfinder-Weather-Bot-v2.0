package pathfinder.weatherBot.time

import pathfinder.weatherBot.weather.Temperature
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

class Day(val forecast: Forecast, val date: LocalDate, oldTemp: Temperature? = null) : Serializable {
    val season = Season(date)
    val temperature = Temperature(this, oldTemp)
    val hours: Array<Hour> = (0..23).fold(emptyArray()) { o, i ->
        o + Hour(this, LocalTime.of(i, 0), o.lastOrNull())
    }

    fun next() = Day(forecast, date.plusDays(1), temperature)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Day

        if (!hours.contentEquals(other.hours)) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = hours.contentHashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}