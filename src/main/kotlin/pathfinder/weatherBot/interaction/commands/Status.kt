package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client

@Service
class Status : WeatherCommand("status", "Returns the status of the bot.") {
    override val sudo = false

    override fun execute(event: SlashCommandEvent, client: Client) = "The bot is ${client.status()}."
}
