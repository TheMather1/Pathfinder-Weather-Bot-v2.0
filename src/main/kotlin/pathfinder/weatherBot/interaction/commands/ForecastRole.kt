package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client
import javax.annotation.PostConstruct

@Service
class ForecastRole : WeatherCommand("forecast_role", "Sets the role allowed to view the forecast.") {

    @PostConstruct
    fun configureOptions() {
        addOption(OptionType.ROLE, "role", "Role to view forecast.")
    }

    override val sudo = true

    override fun execute(event: SlashCommandEvent, client: Client): String {
        return event.getOption("role")?.asRole?.let { role ->
            client.config.forecastRole = role.idLong
            "Members with the ${role.asMention} role can now view the forecast."
        } ?: "Forecast role has been disabled."
    }
}
