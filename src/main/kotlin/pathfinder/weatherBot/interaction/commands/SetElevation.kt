package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.Command.Choice
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.location.Elevation

@Service
class SetElevation : WeatherCommand {
    override val commandData = CommandData("elevation", "Sets the elevation of the server.").addOptions(
        OptionData(
            OptionType.STRING, "elevation", "The elevation of the region.", true
        ).addChoices(Elevation.values().map { Choice(it.name, it.name) })
    )
    override val sudo = true

    override fun execute(event: SlashCommandEvent, client: Client) = try {
        client.config.elevation = Elevation.valueOf(event.getOption("elevation")!!.asString)
        "Elevation has been set to ${client.config.elevation}."
    } catch (_: Throwable) {
        "That is not a supported climate."
    }
}