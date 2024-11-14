package pathfinder.weatherBot.service

import net.dv8tion.jda.api.JDA
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class BotService(
    val jda: JDA,
    val clientService: ClientService,
    @Value("\${baseUrl}")
    private val baseUrl: String
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Scheduled(cron = "@hourly")
    private fun botProcess() {
        logger.info("Executing scheduled weather report.")
        clientService.perform(jda.guilds) { guild, client ->
            if (client.config.active) {
                logger.debug("Reporting weather for server: ${guild.name}")
                client.reportWeather(jda).let { (report, newEvents) ->
                    report?.queue {
                        logger.debug("Weather updated.")
                    } ?: logger.debug("No update.")
                    if (newEvents) client.config.alertsChannel?.let { guild.getTextChannelById(it) }
                        ?.sendMessage("New [events](${baseUrl}/portal/${guild.idLong}/events) require review!")?.queue()
                }
                client
            } else {
                logger.debug("Skipping server: ${guild.name}")
                null
            }
        }
        logger.debug("Weather report completed.")
    }
}
