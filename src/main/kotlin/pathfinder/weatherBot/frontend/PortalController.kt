package pathfinder.weatherBot.frontend

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.Permission.MANAGE_SERVER
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.interactions.commands.Command
import net.dv8tion.jda.api.interactions.commands.PrivilegeConfig
import net.dv8tion.jda.api.interactions.commands.privileges.IntegrationPrivilege.Type.CHANNEL
import net.dv8tion.jda.api.interactions.commands.privileges.IntegrationPrivilege.Type.ROLE
import net.dv8tion.jda.api.interactions.commands.privileges.IntegrationPrivilege.Type.USER
import org.springframework.http.HttpStatus.FORBIDDEN
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.location.Climate
import pathfinder.weatherBot.location.Elevation
import pathfinder.weatherBot.repository.ClientRepository
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.events.Event
import java.time.ZoneId
import java.time.ZonedDateTime

@Suppress("SameReturnValue")
@Controller
@RequestMapping("/portal")
class PortalController(private val jda: JDA, private val clientRepository: ClientRepository) {

    private val timezoneRegex = Regex("^(Africa|America|Asia|Atlantic|Australia|Europe|Indian|Pacific)/.*")

    @GetMapping
    fun viewPortal(model: Model, @AuthenticationPrincipal user: DiscordUser): String {
        model.asUser(user)
        return "portal"
    }

    @GetMapping("/{guildId}")
    fun viewGuild(
        model: Model,
        @AuthenticationPrincipal
        user: DiscordUser,
        @PathVariable("guildId")
        guildId: Long
    ): String {
        val (_, client, _) = model.authenticateUser(user, guildId, Permissions.USER)
        model.displayWeather(client)
        return "guild"
    }

    @PostMapping("/{guildId}", params = ["start", "!stop"])
    fun startBot(
        model: Model,
        @AuthenticationPrincipal user: DiscordUser,
        @PathVariable("guildId") guildId: Long
    ): String {
        var (_, client, _) = model.authenticateUser(user, guildId, Permissions.USER)
        client.config.active = true
        client = clientRepository.saveAndFlush(client)
        model.displayWeather(client)
        return "guild"
    }

    @PostMapping("/{guildId}", params = ["stop", "!start"])
    fun stopBot(
        model: Model,
        @AuthenticationPrincipal user: DiscordUser,
        @PathVariable("guildId") guildId: Long
    ): String {
        var (_, client, _) = model.authenticateUser(user, guildId, Permissions.USER)
        client.config.active = false
        client = clientRepository.saveAndFlush(client)
        model.displayWeather(client)
        return "guild"
    }

    @GetMapping("/{guildId}/forecast")
    fun viewForecast(
        model: Model, @AuthenticationPrincipal user: DiscordUser, @PathVariable("guildId") guildId: Long
    ): String {
        val (_, client) = model.authenticateUser(user, guildId, Permissions.FORECAST)
        model.addAttribute("forecast", client.forecast)
        return "forecast"
    }

    @DeleteMapping("/{guildId}/forecast")
    fun resetForecast(
        model: Model, @AuthenticationPrincipal user: DiscordUser, @PathVariable("guildId") guildId: Long
    ): String {
        val (_, client) = model.authenticateUser(user, guildId, Permissions.MODERATOR)
        model.addAttribute("forecast", client.resetForecast())
        clientRepository.saveAndFlush(client)
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
        client.config.override(config)
        clientRepository.saveAndFlush(client)
        model.withSettings(config, guild)
        return "settings"
    }

    @GetMapping("/{guildId}/events")
    fun viewEvents(
        model: Model, @AuthenticationPrincipal user: DiscordUser, @PathVariable("guildId") guildId: Long
    ): String {
        val (_, client, _) = model.authenticateUser(user, guildId, Permissions.MODERATOR)
        model.withEvents(client.forecast.allEvents)
        return "events"
    }

    @PostMapping("/{guildId}/events")
    fun updateEvent(
        model: Model,
        @AuthenticationPrincipal user: DiscordUser,
        @PathVariable("guildId") guildId: Long,
        @ModelAttribute("config") form: EventForm
    ): String {
        val (_, client, _) = model.authenticateUser(user, guildId, Permissions.MODERATOR)
        form.events.forEach { formEvent ->
            client.forecast.apply {
                today.hours.firstNotNullOfOrNull { h ->
                    h.events.firstOrNull { formEvent.isEvent(it) }?.apply {
                        active = formEvent.active
                    }
                } ?: tomorrow.hours.firstNotNullOfOrNull { h ->
                    h.events.firstOrNull { formEvent.isEvent(it) }?.apply {
                        active = formEvent.active
                    }
                } ?: dayAfterTomorrow.hours.firstNotNullOfOrNull { h ->
                    h.events.firstOrNull { formEvent.isEvent(it) }?.apply {
                        active = formEvent.active
                    }
                }
            }
        }
        clientRepository.saveAndFlush(client)
        model.withEvents(client.forecast.allEvents)
        return "events"
    }

    private fun Model.withEvents(events: List<Event>) {
        addAttribute("eventForm", EventForm(events))
//        addAttribute("event_keys", events.mapIndexed { i, event ->
//            addAttribute("event_$i", event)
//            "event_$i"
//        })
    }

    private fun Model.withSettings(config: GuildConfig, guild: Guild) {
        addAttribute("config", config)
        addAttribute("climateOptions", Climate.entries)
        addAttribute("elevationOptions", Elevation.entries)
        addAttribute("roleOptions", guild.roles.associate { it.idLong to it.name })
        addAttribute("channelOptions", guild.textChannels.associate { it.idLong to it.name })
        addAttribute("timezoneOptions", ZoneId.getAvailableZoneIds().filter { it.contains(timezoneRegex) }.sorted())
    }

    private fun Model.authenticateUser(
        user: DiscordUser, guildId: Long, permissions: Permissions
    ): Triple<Guild, Client, Member> {
        asUser(user)
        val guild = jda.getGuildById(guildId) ?: throw ResponseStatusException(NOT_FOUND, "Server not found.")
        onGuild(guild)
        val client = clientRepository.findByGuildId(guildId) ?: clientRepository.saveAndFlush(Client.forGuild(guild))
        val member =
            guild.getMember(user) ?: throw ResponseStatusException(FORBIDDEN, "You are not a member of this server.")
        asMember(member, permissions)
        return Triple(guild, client, member)
    }

    private fun Model.asUser(user: DiscordUser) {
        addAttribute("avatar", user.avatarUrl)
        addAttribute("username", user.name)
        addAttribute("servers", user.mutualGuilds.associate { it.name to it.idLong })
    }

    private fun Model.asMember(member: Member, requiredPermissions: Permissions? = null) {
        if (member.hasPermission(MANAGE_SERVER)) {
            addAttribute("canForecast", true)
            addAttribute("isModerator", true)
        } else member.onPrivileges { commands, privileges ->
            if (member.canUse(commands.forecast, privileges, true)) addAttribute("canForecast", true)
            else if (requiredPermissions == Permissions.FORECAST)
                throw ResponseStatusException(FORBIDDEN, "You do not have permission to access to view this page.")
            if (member.canUse(commands.settings, privileges)) addAttribute("isModerator", true)
            else if (requiredPermissions == Permissions.MODERATOR)
                throw ResponseStatusException(FORBIDDEN, "You do not have permission to access to view this page.")
        }
    }

    private fun Model.onGuild(guild: Guild) {
        addAttribute("guild", guild.idLong to guild.name)
    }

    private fun Model.displayWeather(client: Client) {
        displayWeather(client.forecast.today.hours[ZonedDateTime.now(client.config.timezone).hour])
        addAttribute("status", client.status())
    }

    private fun Model.displayWeather(hour: Hour?) {
        addAttribute("temp", hour?.temp?.toString())
        addAttribute("clouds", hour?.weather?.clouds)
        addAttribute("wind", hour?.weather?.wind)
        addAttribute("precip", hour?.weather?.precipitation ?: "None")
    }

    private val List<Command>.forecast
        get() = first { it.name == "forecast" }
    private val List<Command>.settings
        get() = first { it.name == "settings" }

    private fun Member.onPrivileges(action: (List<Command>, PrivilegeConfig) -> Unit) {
        guild.retrieveCommands().and(guild.retrieveCommandPrivileges(), action).complete()
    }

    private fun Member.canUse(command: Command, privileges: PrivilegeConfig, acceptUnconfigured: Boolean = false): Boolean {
        return privileges.getCommandPrivileges(command)?.filter { it.isEnabled }?.run {
            (acceptUnconfigured && isEmpty()) || any { privilege ->
                val userIds = listOf(idLong, user.idLong)
                val roleIds = roles.map { it.idLong }
                privilege.type == CHANNEL || privilege.targetsEveryone()
                        || privilege.type == USER && privilege.idLong in userIds
                        || privilege.type == ROLE && privilege.idLong in roleIds
            }
        } == true
    }
}
