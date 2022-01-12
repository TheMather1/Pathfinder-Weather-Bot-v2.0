package pathfinder.weatherBot.frontend

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.Permission.MANAGE_SERVER
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import org.springframework.http.HttpStatus.*
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.location.Climate
import pathfinder.weatherBot.location.Elevation
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
        val (_, client, _) = model.authenticateUser(user, guildId, Permissions.USER)
        model.displayWeather(client)
        return "guild"
    }

    @GetMapping("/{guildId}/forecast")
    fun viewForecast(
        model: Model, @AuthenticationPrincipal user: DiscordUser, @PathVariable("guildId") guildId: Long
    ): String {
        val (_, client, _) = model.authenticateUser(user, guildId, Permissions.FORECAST)
        return "forecast"
    }

    @GetMapping("/{guildId}/settings")
    fun viewSettings(
        model: Model, @AuthenticationPrincipal user: DiscordUser, @PathVariable("guildId") guildId: Long
    ): String {
        val (guild, client, _) = model.authenticateUser(user, guildId, Permissions.MODERATOR)
        model.withSettings(client.config, guild)
        return "settings"
    }

    @PostMapping("/{guildId}/settings")
    fun updateSettings(
        model: Model,
        @AuthenticationPrincipal user: DiscordUser,
        @PathVariable("guildId") guildId: Long,
        @ModelAttribute("config") config: GuildConfig
    ): String {
        val (guild, client, _) = model.authenticateUser(user, guildId, Permissions.MODERATOR)
        client.config = config
        registrations[guildId] = client
        model.withSettings(config, guild)
        return "settings"
    }

    private fun Model.withSettings(config: GuildConfig, guild: Guild) {
        addAttribute("config", config)
        addAttribute("climateOptions", Climate.values())
        addAttribute("elevationOptions", Elevation.values())
        addAttribute("roleOptions", guild.roles.associate { it.idLong to it.name } + (null to "--Disabled--"))
        addAttribute("channelOptions", guild.textChannels.associate { it.idLong to it.name })
    }

    private fun Model.authenticateUser(
        user: DiscordUser, guildId: Long, permissions: Permissions
    ): Triple<Guild, Client, Member> {
        asUser(user)
        val guild = jda.getGuildById(guildId) ?: throw ResponseStatusException(NOT_FOUND, "Server not found.")
        onGuild(guild)
        val client = registrations.getOrPut(guildId) { Client(guild) }
        val member =
            guild.getMember(user) ?: throw ResponseStatusException(FORBIDDEN, "You are not a member of this server.")
        asMember(member, client)
        if (permissions == Permissions.FORECAST && !member.hasPermission(MANAGE_SERVER) && member.roles.none { it.idLong == client.config.forecastRole } || permissions == Permissions.MODERATOR && !member.hasPermission(
                MANAGE_SERVER
            )) throw ResponseStatusException(FORBIDDEN, "You do not have permission to access to view this page.")
        return Triple(guild, client, member)
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
