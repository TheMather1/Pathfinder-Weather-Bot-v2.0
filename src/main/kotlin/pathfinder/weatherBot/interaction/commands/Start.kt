package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.moderatorPermission
import javax.annotation.PostConstruct

@Service
class Start : WeatherCommand("start", "Starts the bot.") {

    @PostConstruct
    fun configureOptions() {
        defaultPermissions = moderatorPermission
    }

    override fun execute(event: SlashCommandInteractionEvent, client: Client) = client.start()
}
