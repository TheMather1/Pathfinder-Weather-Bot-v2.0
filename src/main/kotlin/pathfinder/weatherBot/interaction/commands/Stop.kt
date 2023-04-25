package pathfinder.weatherBot.interaction.commands

import jakarta.annotation.PostConstruct
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.moderatorPermission

@Service
class Stop : WeatherCommand("stop", "Stops the bot.") {

    @PostConstruct
    fun configureOptions() {
        defaultPermissions = moderatorPermission
    }

    override fun execute(event: SlashCommandInteractionEvent, client: Client) = client.stop()
}
