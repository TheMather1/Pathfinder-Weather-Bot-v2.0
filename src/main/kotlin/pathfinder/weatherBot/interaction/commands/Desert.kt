package pathfinder.weatherBot.interaction.commands

import com.jagrosh.jdautilities.command.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import org.springframework.stereotype.Service
import pathfinder.weatherBot.service.ClientService

@Service
class Desert(
    private val clientService: ClientService
) : SlashCommandInterface("desert", "Sets the desert boolean of the server.") {

    init {
        options.add(OptionData(OptionType.BOOLEAN, "desert", "whether the region is a desert", true))
    }

    override fun execute(event: SlashCommandEvent) {
        event.deferReply(true).queue { hook ->
            val desert = event.getOption("desert")!!.asBoolean
            clientService.perform(event.guild!!) { client ->
                client.config.desert = desert
            }
            hook.editOriginal("Desert set to $desert.").queue()
        }
    }
}
