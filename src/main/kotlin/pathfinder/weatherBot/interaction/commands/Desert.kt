package pathfinder.weatherBot.interaction.commands

import jakarta.annotation.PostConstruct
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.moderatorPermission

@Service
class Desert : WeatherCommand("desert", "Sets the desert boolean of the server.") {

    @PostConstruct
    fun configureOptions() {
        addOption(OptionType.BOOLEAN, "desert", "whether the region is a desert", true)
        defaultPermissions = moderatorPermission
    }

    override fun execute(event: SlashCommandInteractionEvent, client: Client): String {
        val desert = event.getOption("desert")!!.asBoolean
        client.config.desert = desert
        return "Desert set to $desert."
    }
}
