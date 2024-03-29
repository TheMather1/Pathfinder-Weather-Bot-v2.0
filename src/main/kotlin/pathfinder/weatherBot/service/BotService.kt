package pathfinder.weatherBot.service

import jakarta.annotation.PostConstruct
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.events.guild.GuildReadyEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.mapdb.DB
import org.mapdb.HTreeMap
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.interaction.commands.WeatherCommand

@Service
class BotService(val jda: JDA, val registrations: HTreeMap<Long, Client>, val weatherCommands: List<WeatherCommand>, val fileDB: DB) :
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
        jda.guilds.forEach { guild ->
            val client = registrations[guild.idLong] ?: Client(guild)
            if (client.config.active) {
                logger.debug("Reporting weather for server: ${guild.name}")
                client.reportWeather(jda)?.queue {
                    logger.debug("Weather updated.")
                } ?: logger.debug("No update.")
            } else logger.debug("Skipping server: ${guild.name}")
            registrations[guild.idLong] = client
            fileDB.commit()
        }
        logger.debug("Weather report completed.")
    }

    override fun onGuildReady(event: GuildReadyEvent) {
        registrations.computeIfAbsent(event.guild.idLong) { Client(event.guild) }
        fileDB.commit()
    }

    override fun onGuildJoin(event: GuildJoinEvent) {
        registrations.computeIfAbsent(event.guild.idLong) { Client(event.guild) }
        fileDB.commit()
    }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        val guild = event.guild!!
        logger.debug("Received command ${event.name} with options ${event.options} from server ${guild.name}.")
        val command = weatherCommands.first { it.name == event.name }
        event.deferReply(command.ephemeral).queue {
            val client = registrations[guild.idLong] ?: Client(guild)
            it.editOriginal(command.execute(event, client)).queue()
            registrations[guild.idLong] = client
            fileDB.commit()
        }
        logger.debug("Command finished processing.")
    }

}
