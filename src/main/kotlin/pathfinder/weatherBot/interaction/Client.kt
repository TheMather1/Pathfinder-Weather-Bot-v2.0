package pathfinder.weatherBot.interaction

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild
import pathfinder.weatherBot.time.Forecast
import java.io.Serializable
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

class Client(
    guild: Guild
) : Serializable {
    internal val config = GuildConfig(guild.defaultChannel!!.idLong)
    internal val forecast = Forecast(config)

    @Transient
    private var timer = Timer()

    init {
        if (config.active) schedule()
    }

    private val now
        get() = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS)
    private val isMidnight
        get() = now.toLocalTime() == LocalTime.MIDNIGHT
    private val today
        get() = forecast.apply { if (isMidnight) progress() }.today
    private val thisHour
        get() = today.hours[now.hour]

    fun start() =
        if (config.active) "The bot is already running."
        else {
            schedule()
            config.active = true
            "Started bot."
        }

    fun stop() =
        if (config.active) {
            timer.cancel()
            timer = Timer()
            config.active = false
            "Stopped bot."
        } else "The bot is not currently running."

    val status
        get() = if (config.active) "running" else "stopped"

    private fun execute() = thisHour.description.takeUnless(String::isEmpty)?.let {
        jda.getTextChannelById(config.outputChannel)!!.sendMessage(it)
    }

    private fun schedule() {
        val nextHour = Date.from(now.withMinute(0).withSecond(0).plusHours(1).toInstant(ZoneOffset.UTC))
        timer.scheduleAtFixedRate(nextHour, ChronoUnit.HOURS.duration.toMillis()) { execute() }
    }

    companion object {
        lateinit var jda: JDA
    }
}