package pathfinder.weatherBot.frontend

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.Permission.MANAGE_SERVER
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import org.springframework.http.HttpStatus.*
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ResponseStatusException
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Weather
import java.time.LocalTime
import java.util.concurrent.ConcurrentMap

@Controller
@RequestMapping("/portal")
class PortalController(private val jda: JDA, private val registrations: ConcurrentMap<Long, Client>) {

    @GetMapping
    fun viewPortal(model: Model, @AuthenticationPrincipal user: DiscordUser): String {
        model.asUser(user)
        return "portal"
    }

    @GetMapping("/{guildId}")
    fun viewGuild(
        model: Model, @AuthenticationPrincipal user: DiscordUser, @PathVariable("guildId") guildId: Long
    ): String {
        model.asUser(user)
        val guild = jda.getGuildById(guildId)
            ?: throw ResponseStatusException(NOT_FOUND, "Server not found.")
        model.onGuild(guild)
        val client = registrations.getOrPut(guildId) { Client(guild) }
        val member = guild.getMember(user)
            ?: throw ResponseStatusException(FORBIDDEN, "You are not a member of this server.")
        model.asMember(member, client)
        model.displayWeather(registrations.getOrPut(guildId) { Client(guild) })
        return "guild"
    }

    @GetMapping("/{guildId}/forecast")
    fun viewForecast(
        model: Model, @AuthenticationPrincipal user: DiscordUser, @PathVariable("guildId") guildId: Long
    ): String {
        model.asUser(user)
        val guild = jda.getGuildById(guildId)
            ?: throw ResponseStatusException(NOT_FOUND, "Server not found.")
        model.onGuild(guild)
        val client = registrations.getOrPut(guildId) { Client(guild) }
        val member = guild.getMember(user)
            ?: throw ResponseStatusException(FORBIDDEN, "You are not a member of this server.")
        model.asMember(member, client)
        if (!member.hasPermission(MANAGE_SERVER) && member.roles.none { it.idLong == client.config.forecastRole })
            throw ResponseStatusException(FORBIDDEN, "You do not have permission to read the forecast.")
        return "forecast"
    }

    @GetMapping("/{guildId}/settings")
    fun viewSettings(
        model: Model, @AuthenticationPrincipal user: DiscordUser, @PathVariable("guildId") guildId: Long
    ): String {
        model.asUser(user)
        val guild = jda.getGuildById(guildId)
            ?: throw ResponseStatusException(NOT_FOUND, "Server not found.")
        model.onGuild(guild)
        val client = registrations.getOrPut(guildId) { Client(guild) }
        val member = guild.getMember(user)
            ?: throw ResponseStatusException(FORBIDDEN, "You are not a member of this server.")
        model.asMember(member, client)
        if (!member.hasPermission(MANAGE_SERVER))
            throw ResponseStatusException(FORBIDDEN, "You do not have permission to access the settings.")
        return "settings"
    }

    private fun Model.asUser(user: DiscordUser) {
        addAttribute("servers", user.mutualGuilds.associate { it.name to it.idLong })
    }

    private fun Model.asMember(member: Member, client: Client) {
        if (member.hasPermission(MANAGE_SERVER)) {
            addAttribute("forecast", true)
            addAttribute("settings", true)
        } else if (member.roles.any { it.idLong == client.config.forecastRole }) addAttribute("forecast", true)
    }

    private fun Model.onGuild(guild: Guild) {
        addAttribute("guild", mapOf("id" to guild.idLong, "name" to guild.name))
    }

    private fun Model.displayWeather(client: Client) {
        displayWeather(client.forecast.today.hours[LocalTime.now().hour])
    }

    private fun Model.displayWeather(hour: Hour?) {
        addAttribute("temp", hour?.temp?.toString())
        displayWeather(hour?.weather)
    }

    private fun Model.displayWeather(weather: Weather?) {
        addAttribute(
            "clouds", weather?.clouds?.name?.capitalizedLowercase(true)
        )
        addAttribute(
            "wind", weather?.wind?.name?.capitalizedLowercase(true)
        )
        addAttribute("precip", weather?.precipitation?.let {
            it::class.simpleName?.capitalizedLowercase()
        } ?: "None")
    }

    private fun String.capitalizedLowercase(enum: Boolean = false) = mapIndexed { index, c ->
        when {
            index == 0 -> c.uppercase()
            c.isUpperCase() -> if (enum) c.lowercase() else " " + c.lowercase()
            c == '_' -> if (enum) " " else c.toString()
            else -> c.toString()
        }
    }.reduce(String::plus)
}
