package pathfinder.weatherBot.interaction.commands

import com.jagrosh.jdautilities.command.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import org.springframework.stereotype.Service
import pathfinder.weatherBot.service.ClientService

@Service
class ForecastRole(
    private val clientService: ClientService
) : SlashCommandInterface("forecast_role", "Sets the role allowed to view the forecast.") {

    init {
        options.add(OptionData(OptionType.ROLE, "role", "Role to view forecast."))
    }

    override fun execute(event: SlashCommandEvent) {
        event.deferReply(true).queue { hook ->
            val role = event.getOption("role")?.asRole
            clientService.perform(event.guild!!) { client ->
                client.config.forecastRole = role?.idLong
            }
            if (role != null) hook.editOriginal("Members with the ${role.asMention} role can now view the forecast.").queue()
            else hook.editOriginal("Forecast role has been disabled.").queue()
        }
    }
}
