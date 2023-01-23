package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client

@Service
class Weather : WeatherCommand("weather", "Prints the current weather.") {
    override val ephemeral = false

    override fun execute(event: SlashCommandInteractionEvent, client: Client) = client.thisHour?.singleDescription
        ?: "The current weather has not been defined."
}
