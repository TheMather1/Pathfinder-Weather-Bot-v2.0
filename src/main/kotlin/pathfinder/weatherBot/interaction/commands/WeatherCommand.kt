package pathfinder.weatherBot.interaction.commands

import com.jagrosh.jdautilities.command.SlashCommandEvent
import org.springframework.stereotype.Service
import pathfinder.weatherBot.service.ClientService

@Service
class WeatherCommand(
    private val clientService: ClientService
) : SlashCommandInterface("weather", "Prints the current weather.") {

    override fun execute(event: SlashCommandEvent) {
        event.deferReply().queue { hook ->
            clientService.perform(event.guild!!) { client ->
                hook.editOriginalEmbeds(client.thisHour.embed).queue()
            }
        }
    }
}
