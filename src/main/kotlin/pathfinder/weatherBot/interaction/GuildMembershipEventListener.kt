package pathfinder.weatherBot.interaction

import net.dv8tion.jda.api.events.guild.GuildBanEvent
import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent
import net.dv8tion.jda.api.events.guild.GuildReadyEvent
import net.dv8tion.jda.api.events.guild.GuildUnavailableEvent
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import pathfinder.weatherBot.service.ClientService

@Service
class GuildMembershipEventListener(val clientService: ClientService): ListenerAdapter() {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun onGuildReady(event: GuildReadyEvent) {
        if (clientService.createIfAbsent(event.guild))
            logger.warn("Guild ${event.guild.name} missing data. Added client.")
    }

    override fun onGuildJoin(event: GuildJoinEvent) {
        if (clientService.createIfAbsent(event.guild))
            logger.debug("Joined guild ${event.guild.name}. Added client.")
        else
            logger.warn("Joined guild with existing data ${event.guild.name}. Guild id: ${event.guild.idLong}.")
    }

    override fun onGuildLeave(event: GuildLeaveEvent) {
        if (clientService.delete(event.guild))
            logger.debug("Left guild ${event.guild.name}. Deleting data.")
        else
            logger.warn("Left guild ${event.guild.name}. No data to delete.")
    }

    override fun onGuildBan(event: GuildBanEvent) {
        if (event.user.idLong == event.jda.selfUser.idLong) {
            if (clientService.delete(event.guild))
                logger.debug("Banned from guild ${event.guild.name}. Deleting data.")
            else
                logger.warn("Banned from guild ${event.guild.name}. No data to delete.")
        }
    }

    override fun onGuildMemberRemove(event: GuildMemberRemoveEvent) {
        if (event.user.idLong == event.jda.selfUser.idLong) {
            if (clientService.delete(event.guild))
                logger.debug("Kicked from guild ${event.guild.name}. Deleting data.")
            else
                logger.warn("Kicked from guild ${event.guild.name}. No data to delete.")
        }
    }

    override fun onGuildUnavailable(event: GuildUnavailableEvent) {
        logger.warn("Guild ${event.guild.name} unavailable.")
    }
}