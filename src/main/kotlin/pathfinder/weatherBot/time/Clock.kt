package pathfinder.weatherBot.time

import pathfinder.weatherBot.bot.Bot
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.LocalTime.MIDNIGHT
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit.HOURS
import java.util.*
import kotlin.concurrent.timerTask

class Clock(private val bot: Bot) {
    private var active = false
    private var timer = Timer()
    private val calendar = Calendar(bot.location)
    private val now: LocalDateTime
        get() = now().truncatedTo(HOURS)

    fun start() =
            if (active) "The bot is already running."
            else {
                schedule()
                active = true
                "Started bot."
            }

    fun stop() =
            if (active) {
                timer.cancel()
                timer = Timer()
                active = false
                "Stopped bot."
            } else "The bot is not currently running."

    val status
        get() = if (active) "running." else "stopped."

    private fun execute() {
        if (now.toLocalTime() == MIDNIGHT) calendar.nextDay()
        val hour = Hour(calendar, now)
        bot.post(message = TODO(hour.execute))
    }

    private fun schedule() {
        val nextHour = Date.from(now.plusHours(1).toInstant(ZoneOffset.UTC))
        val task = timerTask { execute() }
        timer.scheduleAtFixedRate(task, nextHour, HOURS.duration.toMillis())
    }
}