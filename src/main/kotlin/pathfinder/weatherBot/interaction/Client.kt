package pathfinder.weatherBot.interaction

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild
import pathfinder.weatherBot.time.Forecast
import java.io.Serializable
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class Client(
    guild: Guild
) : Serializable {
    internal var config = GuildConfig(guild.defaultChannel!!.idLong)
    internal val forecast = Forecast(config)

    private val now
        get() = ZonedDateTime.now(config.timezone).truncatedTo(ChronoUnit.HOURS)
    private val isMidnight
        get() = now.hour == 0
    private val today
        get() = forecast.apply { if (isMidnight) advanceDay(config) }.today
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

    fun reportWeather(jda: JDA) = thisHour?.report?.let {
        jda.getTextChannelById(config.outputChannel)!!.sendMessage(it)
    }
}
