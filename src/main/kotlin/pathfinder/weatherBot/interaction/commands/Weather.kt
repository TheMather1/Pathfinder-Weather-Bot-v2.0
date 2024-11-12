package pathfinder.weatherBot.interaction.commands

import com.jagrosh.jdautilities.command.SlashCommandEvent
import org.springframework.stereotype.Service
import pathfinder.weatherBot.service.ClientService

@Service
class Weather(
    private val clientService: ClientService
) : SlashCommandInterface("weather", "Prints the current weather.") {

    override fun execute(event: SlashCommandEvent) {
        event.deferReply().queue { hook ->
            clientService.perform(event.guild!!) { client ->
                hook.editOriginal(
                    client.thisHour?.description
                    ?: "The current weather has not been defined."
                ).queue()
            }
        }
    }
}
