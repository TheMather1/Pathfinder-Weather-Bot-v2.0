package pathfinder.weatherBot.interaction.commands

import com.jagrosh.jdautilities.command.SlashCommandEvent
import org.springframework.stereotype.Service
import pathfinder.weatherBot.service.ClientService

@Service
class Start(
    private val clientService: ClientService
) : SlashCommandInterface("start", "Starts the bot.") {

    override fun execute(event: SlashCommandEvent) {
        event.deferReply(true).queue { hook ->
            clientService.perform(event.guild!!) { client ->
                hook.editOriginal(client.start()).queue()
            }
        }
    }
}
