package pathfinder.weatherBot.weather.precipitation

import pathfinder.weatherBot.d
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
        private fun dry(season: Season): Boolean = (1 d 100) > season.frequency().chance
        private fun frozen(temp: Long): Boolean = temp <= 32
        private var prevEnd: LocalDateTime? = null
        fun thunder(): Boolean = (1 d 100) <= 10

        operator fun invoke(
            season: Season,
            temp: Long,
            date: LocalDate,
            prevEnd: LocalDateTime?
        ): Precipitation? = when {
            dry(season) -> null
            frozen(temp) -> Frozen(temp, date)
            else -> Wet(temp, date)
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

