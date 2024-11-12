package pathfinder.weatherBot.interaction.commands

import com.jagrosh.jdautilities.command.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.Command.Choice
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import org.springframework.stereotype.Service
import pathfinder.weatherBot.location.Climate
import pathfinder.weatherBot.service.ClientService

@Service
class SetClimate(
    private val clientService: ClientService
) : SlashCommandInterface("climate", "Sets the climate of the server.") {

    init {
        options.add(
            OptionData(
                OptionType.STRING, "climate", "The climate of the region.", true
            ).addChoices(Climate.entries.map { Choice(it.name, it.name) })
        )
    }

    override fun execute(event: SlashCommandEvent) {
        event.deferReply(true).queue { hook ->
            try {
                val climate = event.getOption("climate") { Climate.valueOf(it.asString) }!!
                clientService.perform(event.guild!!) { client ->
                    client.config.climate = climate
                }
                hook.editOriginal("Climate has been set to $climate.")
            } catch (_: Throwable) {
                hook.editOriginal("That is not a supported climate.")
            }.queue()
        }
    }
}
