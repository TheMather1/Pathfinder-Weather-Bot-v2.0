package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.moderatorPermission
import javax.annotation.PostConstruct

@Service
class ForecastRole : WeatherCommand("forecast_role", "Sets the role allowed to view the forecast.") {

    @PostConstruct
    fun configureOptions() {
        addOption(OptionType.ROLE, "role", "Role to view forecast.")
        defaultPermissions = moderatorPermission
    }

    override fun execute(event: SlashCommandInteractionEvent, client: Client): String {
        return event.getOption("role")?.asRole.also {
            client.config.forecastRole = it?.idLong
        }?.run { "Members with the $asMention role can now view the forecast." }
            ?: "Forecast role has been disabled."
    }
}
