package pathfinder.weatherBot.interaction

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild
import org.hibernate.annotations.NaturalId
import pathfinder.weatherBot.time.Forecast
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

@Entity(name = "CLIENTS")
class Client(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @NaturalId
    val guildId: Long,
    @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, orphanRemoval = true)
    val config: GuildConfig,
    @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, orphanRemoval = true)
    var forecast: Forecast
) {

    private val now
        get() = ZonedDateTime.now(config.timezone).truncatedTo(ChronoUnit.HOURS)
    private val isMidnight
        get() = now.hour == 0
    val thisHour
        get() = if(isMidnight) forecast.tomorrow.hours[now.hour] else forecast.today.hours[now.hour]
    val lastHour
        get() = forecast.today.hours[if (isMidnight) 23 else now.hour - 1]

    fun start() = if (!config.active) {
        config.active = true
        "Started bot."
    } else "The bot is already running."

    fun stop() = if (config.active) {
        config.active = false
        "Stopped bot."
    } else "The bot is not currently running."

    fun status() = if (config.active) "running" else "stopped"

    fun resetForecast() = Forecast.fromConfig(config).also {
        forecast = it
    }

    fun reportWeather(jda: JDA, dryRun: Boolean = false) = thisHour?.report(lastHour)?.let {
        jda.getTextChannelById(config.outputChannel)!!.sendMessage(it)
    }.also {
        if (isMidnight && !dryRun) forecast.advanceDay(config)
    }

    companion object {
        fun forGuild(guild: Guild): Client {
            val config = GuildConfig(outputChannel = guild.defaultChannel!!.idLong)
            return Client(0, guild.idLong, config, Forecast.fromConfig(config))
        }
    }
}
