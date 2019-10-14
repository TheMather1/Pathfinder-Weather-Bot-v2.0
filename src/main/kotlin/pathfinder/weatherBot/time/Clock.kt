package pathfinder.weatherBot.time

import pathfinder.weatherBot.bot.Bot
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.LocalTime.MIDNIGHT
import java.time.ZoneOffset
import java.util.*
import kotlin.concurrent.timerTask

class Clock(private val bot: Bot) {
    private var active = false
    private var timer = Timer()
    private val calendar = Calendar(bot.location)
    private val now: LocalDateTime
        get() = now().withMinute(0)

    fun start(): String = if (active) "The bot is already running." else execute().let { active = true; "Started bot." }
    fun stop(): String  = if (active) timer.cancel().let { timer = Timer(); active = false; "Stopped bot." } else "The bot is not currently running."
    fun status() = if(active) "running." else "stopped."

    private fun execute() {
        if (now.toLocalTime() == MIDNIGHT) calendar.nextDay()
        val hour = Hour(calendar, now)
        bot.post(message = TODO(hour.execute))
        schedule()
    }

    private fun schedule() {
        val nextHour = Date.from(now.plusHours(1).toInstant(ZoneOffset.UTC))
        val task = timerTask { execute() }
        timer.schedule(task, nextHour)
    }
}