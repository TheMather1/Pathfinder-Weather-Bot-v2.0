package pathfinder.weatherBot.interaction

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild
import pathfinder.weatherBot.time.Forecast
import java.io.Serializable
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit

class Client(
    guild: Guild
) : Serializable {
    internal var config = GuildConfig(guild.defaultChannel!!.idLong)
    internal val forecast = Forecast(config)

    private val now
        get() = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS)
    private val isMidnight
        get() = now.toLocalTime() == LocalTime.MIDNIGHT
    private val today
        get() = forecast.apply { if (isMidnight) progress(config) }.today
    val thisHour
        get() = today.hours[now.hour]

    fun start() = if (!config.active) {
        config.active = true
        "Started bot."
    } else "The bot is already running."

    fun stop() = if (config.active) {
        config.active = false
        "Stopped bot."
    } else "The bot is not currently running."

    fun status() = if (config.active) "running" else "stopped"

    fun execute(jda: JDA) = thisHour?.description?.takeUnless(String::isBlank)?.let {
        jda.getTextChannelById(config.outputChannel)!!.sendMessage(it)
    }
}
