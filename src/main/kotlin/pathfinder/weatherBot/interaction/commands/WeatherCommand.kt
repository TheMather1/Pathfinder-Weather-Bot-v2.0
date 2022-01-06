package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import pathfinder.weatherBot.interaction.Client

interface WeatherCommand {
    val commandData: CommandData
    val sudo: Boolean

    fun execute(event: SlashCommandEvent, client: Client): String
}