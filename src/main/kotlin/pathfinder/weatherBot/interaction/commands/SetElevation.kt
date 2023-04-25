package pathfinder.weatherBot.interaction.commands

import jakarta.annotation.PostConstruct
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.Command.Choice
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.location.Elevation
import pathfinder.weatherBot.moderatorPermission

@Service
class SetElevation : WeatherCommand("elevation", "Sets the elevation of the server.") {

    @PostConstruct
    fun configureOptions() {
        addOptions(
            OptionData(
                OptionType.STRING, "elevation", "The elevation of the region.", true
            ).addChoices(Elevation.values().map { Choice(it.name, it.name) })
        )
        defaultPermissions = moderatorPermission
    }

    override fun execute(event: SlashCommandInteractionEvent, client: Client) = try {
        client.config.elevation = event.getOption("elevation") { Elevation.valueOf(it.asString) }!!
        "Elevation has been set to ${client.config.elevation}."
    } catch (_: Throwable) {
        "That is not a supported climate."
    }
}
