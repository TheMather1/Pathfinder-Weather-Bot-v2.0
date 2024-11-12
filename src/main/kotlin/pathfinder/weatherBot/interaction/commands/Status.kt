package pathfinder.weatherBot.interaction.commands

import com.jagrosh.jdautilities.command.SlashCommandEvent
import org.springframework.stereotype.Service
import pathfinder.weatherBot.service.ClientService

@Service
class Status(
    private val clientService: ClientService
) : SlashCommandInterface("status", "Returns the status of the bot.") {

    override fun execute(event: SlashCommandEvent) {
        event.deferReply(true).queue { hook ->
            clientService.perform(event.guild!!) { client ->
                hook.editOriginal("The bot is ${client.status()}.").queue()
            }
        }
    }
}
