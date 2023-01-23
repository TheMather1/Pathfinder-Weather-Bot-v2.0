package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.Command.Choice
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.location.Climate
import pathfinder.weatherBot.moderatorPermission
import javax.annotation.PostConstruct

@Service
class SetClimate : WeatherCommand("climate", "Sets the climate of the server.") {

    @PostConstruct
    fun configureOptions() {
        addOptions(
            OptionData(
                OptionType.STRING, "climate", "The climate of the region.", true
            ).addChoices(Climate.values().map { Choice(it.name, it.name) })
        )
        defaultPermissions = moderatorPermission
    }

    override fun execute(event: SlashCommandInteractionEvent, client: Client) = try {
        client.config.climate = event.getOption("climate") { Climate.valueOf(it.asString) }!!
        "Climate has been set to ${client.config.climate}."
    } catch (_: Throwable) {
        "That is not a supported climate."
    }
}
