package pathfinder.weatherBot.weather.precipitation

import pathfinder.weatherBot.d
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.controller.frozen.Frozen
import pathfinder.weatherBot.weather.precipitation.controller.wet.Wet
import pathfinder.weatherBot.time.Season
import pathfinder.weatherBot.time.TimeFrame
import pathfinder.weatherBot.weather.events.Event
import java.time.*
import java.time.LocalTime.MIDNIGHT
import java.time.LocalTime.NOON

interface Precipitation {
    companion object{
        private fun dry(location: Location, season: Season): Boolean = (1 d 100) > season.frequency(location).chance
        private fun frozen(temp: Long): Boolean = temp <= 32
        private var prevEnd: LocalDateTime? = null
        fun thunder(): Boolean = (1 d 100) <= 10

        operator fun invoke(
            location: Location,
            season: Season,
            temp: Long,
            date: LocalDate,
            prevEnd: LocalDateTime?
        ): Precipitation? = when {
            dry(location, season) -> null
            frozen(temp) -> Frozen(location, temp, date)
            else -> Wet(location, temp, date)
        }.also { Companion.prevEnd = prevEnd }

    }
    fun print(prev: Precipitation?): String
    fun finished(): String
    val date: LocalDate
    val hours: Long
    private val start: LocalDateTime
        get() = date.atTime((if ((1 d 6) > 3) MIDNIGHT else NOON).run {
            plusHours(
                if (this == MIDNIGHT && prevEnd != null) (1 d 12- prevEnd!!.hour)+ prevEnd!!.hour
                else 1 d 12)
        })
    val end: LocalDateTime
        get() = start.plusHours(hours)
    val timeFrame: TimeFrame
    get() = TimeFrame(start, end)
    val tempAdjust: Long
    get() = 0

    fun fall(){}
    fun events(): List<Event>{ return emptyList() }
}

