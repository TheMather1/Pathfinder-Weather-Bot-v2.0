package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.internal.interactions.CommandDataImpl
import pathfinder.weatherBot.interaction.Client

abstract class WeatherCommand(name: String, description: String) : CommandDataImpl(name, description) {
    abstract val sudo: Boolean

    abstract fun execute(event: SlashCommandInteractionEvent, client: Client): String
}
