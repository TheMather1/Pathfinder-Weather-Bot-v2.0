package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.Command.Choice
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.location.Climate

@Service
class SetClimate : WeatherCommand {
    override val commandData = CommandData("climate", "Sets the climate of the server.").addOptions(
        OptionData(
            OptionType.STRING, "climate", "The climate of the region.", true
        ).addChoices(Climate.values().map { Choice(it.name, it.name) })
    )
    override val sudo = true

    override fun execute(event: SlashCommandEvent, client: Client) = try {
        client.config.climate = Climate.valueOf(event.getOption("climate")!!.asString)
        "Climate has been set to ${client.config.climate}."
    } catch (_: Throwable) {
        "That is not a supported climate."
    }
}