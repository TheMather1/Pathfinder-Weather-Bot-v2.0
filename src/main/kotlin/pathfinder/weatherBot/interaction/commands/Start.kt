package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client

@Service
class Start : WeatherCommand{
    override val commandData = CommandData("start", "Starts the bot.")
    override val sudo = true

    override fun execute(event: SlashCommandEvent, client: Client) = client.start()
}