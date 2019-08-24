package weatherBot.time

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.LocalTime.MIDNIGHT
import java.time.ZoneOffset
import java.util.*
import kotlin.concurrent.timerTask

object Clock {
    private val timer = Timer()

    private fun execute() {
        if (LocalTime.now() == MIDNIGHT) Schedule.nextDay()
        val precipitation = Schedule.activePrecipitation
        val prevPrecipitation = Schedule.prevPrecipitation
        val weather = Schedule.weather
        precipitation?.fall()
        val precipitationDescription = if(precipitation != prevPrecipitation) precipitation?.print(prevPrecipitation) ?: prevPrecipitation?.finished() else null
        val cloudDescription = weather.clouds.description
    }

    fun schedule() {
        val nextHour = Date.from(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.UTC))
        val task = timerTask { execute(); schedule() }
        timer.schedule(task, nextHour)
    }
}