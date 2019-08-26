package weatherBot.time

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
        val weather = Schedule.weather
        val precipitation = Schedule.activePrecipitation?.apply { fall() }
        val prevPrecipitation = Schedule.prevPrecipitation
        val precipitationDescription = if(precipitation != prevPrecipitation) precipitation?.print(prevPrecipitation) ?: prevPrecipitation?.finished() else null
        val cloudDescription = weather.clouds.description
        val temp = weather.currentTemp(LocalTime.now())
        val extremeTempDescription = when(temp + (precipitation?.tempAdjust ?: 0)){
            in Long.MIN_VALUE..40 -> TODO("Hypothermia")
            in 90..Long.MAX_VALUE -> TODO("Heatstroke")
            else -> null
        }
    }

    fun schedule() {
        val nextHour = Date.from(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.UTC))
        val task = timerTask { execute(); schedule() }
        timer.schedule(task, nextHour)
    }
}