package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client

@Service
class Start : WeatherCommand("start", "Starts the bot.") {
    override val sudo = true

    override fun execute(event: SlashCommandInteractionEvent, client: Client) = client.start()
}
