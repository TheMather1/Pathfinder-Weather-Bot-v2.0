package pathfinder.weatherBot.time

import pathfinder.weatherBot.interaction.Client
import java.time.LocalDateTime.now
import java.time.LocalTime.MIDNIGHT
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit.HOURS
import java.util.*
import kotlin.concurrent.timerTask

class Clock(private val client: Client) {
    private var active = false
    private var timer = Timer()

    private val now
        get() = now().truncatedTo(HOURS)
    private val isMidnight
        get() = now.toLocalTime() == MIDNIGHT
    private val today
        get() = client.forecast.apply { if (isMidnight) progress() }.today
    private val thisHour
        get() = today.hours[now.hour]

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
        get() = if (active) "running" else "stopped"

    private fun execute() = client.outputChannel.sendMessage(thisHour.description)

    private fun schedule() {
        val nextHour = Date.from(now.plusHours(1).toInstant(ZoneOffset.UTC))
        val task = timerTask { execute() }
        timer.scheduleAtFixedRate(task, nextHour, HOURS.duration.toMillis())
    }
}