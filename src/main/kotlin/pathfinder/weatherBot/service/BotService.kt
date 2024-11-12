package pathfinder.weatherBot.service

import jakarta.annotation.PostConstruct
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.guild.GuildBanEvent
import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent
import net.dv8tion.jda.api.events.guild.GuildReadyEvent
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.commands.WeatherCommand

@Service
class BotService(val jda: JDA, val clientService: ClientService, val weatherCommands: List<WeatherCommand>) :
    ListenerAdapter() {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostConstruct
    fun startBot() {
        jda.addEventListener(this)
        jda.awaitReady()
        jda.updateCommands().addCommands(weatherCommands.map { it }).queue { commands ->
            logger.debug("Registered commands ${commands.map { it.name }}.")
        }
    }

    @Scheduled(cron = "@hourly")
    private fun botProcess() {
        logger.info("Executing scheduled weather report.")
        clientService.perform(jda.guilds) { guild, client ->
            if (client.config.active) {
                logger.debug("Reporting weather for server: ${guild.name}")
                client.reportWeather(jda)?.queue {
                    logger.debug("Weather updated.")
                } ?: logger.debug("No update.")
                client
            } else {
                logger.debug("Skipping server: ${guild.name}")
                null
            }
        }
        logger.debug("Weather report completed.")
    }

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
        if (event.user.idLong == jda.selfUser.idLong) {
            if (clientService.delete(event.guild))
                logger.debug("Banned from guild ${event.guild.name}. Deleting data.")
            else
                logger.warn("Banned from guild ${event.guild.name}. No data to delete.")
        }
    }

    override fun onGuildMemberRemove(event: GuildMemberRemoveEvent) {
        if (event.user.idLong == jda.selfUser.idLong) {
            if (clientService.delete(event.guild))
                logger.debug("Kicked from guild ${event.guild.name}. Deleting data.")
            else
                logger.warn("Kicked from guild ${event.guild.name}. No data to delete.")
        }
    }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        val guild = event.guild!!
        logger.debug("Received command ${event.name} with options ${event.options} from server ${guild.name}.")
        val command = weatherCommands.first { it.name == event.name }
        event.deferReply(command.ephemeral).queue { hook ->
            clientService.perform(guild) {
                hook.editOriginal(command.execute(event, it)).queue()
            }
        }
        logger.debug("Command finished processing.")
    }

}
