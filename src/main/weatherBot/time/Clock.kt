package weatherBot.time

import weatherBot.time.Day.Companion.precipitation
import weatherBot.time.Day.Companion.nextDay
import weatherBot.time.Day.Companion.weather
import java.time.LocalDateTime
import java.time.LocalTime.MIDNIGHT
import java.time.LocalDateTime.now
import java.time.ZoneOffset
import java.util.*
import kotlin.concurrent.timerTask

object Clock {
    private var active = false
    private var timer = Timer()
    private val now: LocalDateTime
        get() = now().withMinute(0)

    operator fun invoke() = if (active) println("The bot is already running.") else execute().also { active = true }
    fun stop() = timer.cancel().also { timer = Timer(); active = false }
    fun status() = if(active) "running." else "stopped."

    private fun execute() {
        if (now.toLocalTime() == MIDNIGHT) nextDay()
        val precipitation = precipitation?.apply { fall() }
        val temp = weather.temp(now.toLocalTime())
        schedule()
    }

    private fun schedule() {
        val nextHour = Date.from(now.plusHours(1).toInstant(ZoneOffset.UTC))
        val task = timerTask { execute() }
        timer.schedule(task, nextHour)
    }
}