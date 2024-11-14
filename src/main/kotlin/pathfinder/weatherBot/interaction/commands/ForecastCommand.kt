package pathfinder.weatherBot.interaction.commands

import com.jagrosh.jdautilities.command.SlashCommandEvent
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ForecastCommand(
    @Value("\${baseUrl}")
    private val baseUrl: String
) : SlashCommandInterface("forecast", "Read the weather forecast.") {

    override fun execute(event: SlashCommandEvent) {
        event.deferReply(true).queue {
            it.editOriginal("Forecast can be found [here](${baseUrl}/portal/${event.guild!!.idLong}/forecast)").queue()
        }
    }
}
