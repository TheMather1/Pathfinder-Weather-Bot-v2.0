package pathfinder.weatherBot.time

import net.dv8tion.jda.api.JDA
import org.springframework.beans.factory.annotation.Autowired
import pathfinder.weatherBot.interaction.Client
import java.io.Serializable
import java.time.LocalDateTime.now
import java.time.LocalTime.MIDNIGHT
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit.HOURS
import java.util.*
import kotlin.concurrent.timerTask

class Clock(private val client: Client): Serializable {
    private var active = false
    @Transient
    private var timer = Timer()

    init {
        if (active) schedule()
    }

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

    private fun execute() = thisHour.description.takeUnless(String::isEmpty)?.let {
        jda.getTextChannelById(client.channelId)!!.sendMessage(it)
    }

    private fun schedule() {
        val nextHour = Date.from(now.plusHours(1).toInstant(ZoneOffset.UTC))
        val task = timerTask { execute() }
        timer.scheduleAtFixedRate(task, nextHour, HOURS.duration.toMillis())
    }

    companion object {
        @Autowired
        private lateinit var jda: JDA
    }
}