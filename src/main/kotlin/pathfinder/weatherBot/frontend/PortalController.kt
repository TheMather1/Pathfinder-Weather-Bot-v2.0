package pathfinder.weatherBot.frontend

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.Permission.MANAGE_SERVER
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
        val servers = user.mutualGuilds
        model.addAttribute("servers", servers.associate { it.name to it.idLong })
        return "portal"
    }

    @GetMapping("/{guildId}")
    fun viewGuild(model: Model, @AuthenticationPrincipal user: DiscordUser, @PathVariable("guildId") guildId: Long): String {
        val guild = jda.getGuildById(guildId)
        guild?.loadMembers()?.get()
        when {
            guild == null -> throw ResponseStatusException(NOT_FOUND, "Server not found.")
            !guild.isMember(user) -> throw ResponseStatusException(FORBIDDEN, "You are not a member of this server.")
            else -> model.addAttributes(
                guild.getMember(user)!!,
                registrations.getOrPut(guildId) { Client(guild) }
            )
        }
        model.addAttribute("guild", guildId)
        val servers = user.mutualGuilds
        model.addAttribute("servers", servers.associate { it.name to it.idLong })
        return "guild"
    }

    private fun Model.addAttributes(member: Member, client: Client) {
        if (member.hasPermission(MANAGE_SERVER)) {
            addAttribute("forecast", true)
            addAttribute("config", true)
        } else if (member.roles.any { it.idLong == client.forecastRole }) addAttribute("forecast", true)
        addAttributes(client.forecast.today.hours[LocalTime.now().hour])
    }

    private fun Model.addAttributes(hour: Hour){
        addAttribute("temp", hour.temp)
        addAttributes(hour.weather)
    }

    private fun Model.addAttributes(weather: Weather) {
        addAttribute(
            "clouds", weather.clouds.name.capitalizedLowercase(true)
        )
        addAttribute(
            "wind", weather.wind.name.capitalizedLowercase(true)
        )
        addAttribute("precip", weather.precipitation?.let {
            it::class.simpleName?.capitalizedLowercase()
        } ?: "None")
    }

    private fun String.capitalizedLowercase(enum: Boolean = false) = mapIndexed { index, c ->
        when {
            index == 0 -> c.uppercase()
            c.isUpperCase() -> if(enum) c.lowercase() else " " + c.lowercase()
            c == '_' -> if(enum) " " else c.toString()
            else -> c.toString()
        }
    }.reduce(String::plus)
}