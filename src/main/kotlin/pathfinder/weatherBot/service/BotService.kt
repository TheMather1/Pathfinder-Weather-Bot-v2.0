package pathfinder.weatherBot.service

import net.dv8tion.jda.api.JDA
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class BotService(val jda: JDA, val clientService: ClientService) {
    private val logger = LoggerFactory.getLogger(javaClass)

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
}
