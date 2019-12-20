package pathfinder.weatherBot.weather.precipitation

import pathfinder.weatherBot.d
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.time.Season
import pathfinder.weatherBot.time.TimeFrame
import pathfinder.weatherBot.weather.precipitation.controller.frozen.Frozen
import pathfinder.weatherBot.weather.precipitation.controller.wet.Wet
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime.MIDNIGHT
import java.time.LocalTime.NOON

abstract class Precipitation(val date: LocalDate, val hours: Long) {
    companion object {
        private fun dry(location: Location, season: Season): Boolean = (1 d 100) > season.frequency(location).chance
        private fun frozen(temp: Long): Boolean = temp <= 32
        private var prevEnd: LocalDateTime? = null
        fun thunder(): Boolean = (1 d 100) <= 10

        operator fun invoke(
                location: Location,
                season: Season,
                date: LocalDate,
                prevEnd: LocalDateTime?,
                temp: Long
        ): Precipitation? = when {
            dry(location, season) -> null
            frozen(temp) -> Frozen(location, date, temp)
            else -> Wet(location, date, temp)
        }.also { Companion.prevEnd = prevEnd }

    }

    abstract fun print(prev: Precipitation?): String
    abstract fun finished(): String
    abstract fun fall()

    abstract val fireRetardance: Int

    val start = date.atTime(if ((1 d 6) > 3) MIDNIGHT else NOON)
            .plusHours(1 d 12)
            .coerceAtLeast(prevEnd ?: date.atStartOfDay())!!
    val end = start.plusHours(hours)!!
    val timeFrame = TimeFrame(start, end)
    val tempAdjust = 0
}

