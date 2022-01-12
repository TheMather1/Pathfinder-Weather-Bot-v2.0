package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client

@Service
class Start : WeatherCommand("start", "Starts the bot.") {
    override val sudo = true

    override fun execute(event: SlashCommandEvent, client: Client) = client.start()
}
