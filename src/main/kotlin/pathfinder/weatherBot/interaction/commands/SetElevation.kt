package pathfinder.weatherBot.interaction.commands

import com.jagrosh.jdautilities.command.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.Command.Choice
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import org.springframework.stereotype.Service
import pathfinder.weatherBot.location.Elevation
import pathfinder.weatherBot.service.ClientService

@Service
class SetElevation(
    private val clientService: ClientService
) : SlashCommandInterface("elevation", "Sets the elevation of the server.") {

    init {
        options.add(
            OptionData(
                OptionType.STRING, "elevation", "The elevation of the region.", true
            ).addChoices(Elevation.entries.map { Choice(it.name, it.name) })
        )
    }

    override fun execute(event: SlashCommandEvent) {
        event.deferReply(true).queue { hook ->
            try {
                val elevation = event.getOption("elevation") { Elevation.valueOf(it.asString) }!!
                clientService.perform(event.guild!!) { client ->
                    client.config.elevation = elevation
                }
                hook.editOriginal("Elevation has been set to $elevation.")
            } catch (_: Throwable) {
                hook.editOriginal("That is not a supported elevation.")
            }.queue()
        }
    }
}
