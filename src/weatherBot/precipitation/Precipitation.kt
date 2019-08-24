package weatherBot.precipitation

import weatherBot.d
import weatherBot.precipitation.controller.frozen.Frozen
import weatherBot.precipitation.controller.wet.Wet
import weatherBot.time.Season
import weatherBot.time.TimeFrame
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
        }.also { this.prevEnd = prevEnd }

    }
    fun print(prev: Precipitation?): String
    fun finished(): String
    val date: LocalDate
    val hours: Long
    private val start: LocalDateTime
        get() = date.atTime((if ((1 d 6) > 3) MIDNIGHT else NOON).run {
            plusHours(
                if (this == MIDNIGHT && prevEnd != null) (1 d 12- prevEnd!!.hour)+prevEnd!!.hour
                else 1 d 12)
        })
    val end: LocalDateTime
        get() = start.plusHours(hours)
    val timeFrame: TimeFrame
    get() = TimeFrame(start, end)
    val tempAdjust: Long
    get() = 0

    fun fall(){}
}

