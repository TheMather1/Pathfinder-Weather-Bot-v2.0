package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import pathfinder.weatherBot.interaction.Client

abstract class WeatherCommand(name: String, description: String) : CommandData(name, description) {
    abstract val sudo: Boolean

    abstract fun execute(event: SlashCommandEvent, client: Client): String
}
