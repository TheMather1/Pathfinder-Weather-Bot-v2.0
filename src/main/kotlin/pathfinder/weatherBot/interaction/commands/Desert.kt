package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client
import javax.annotation.PostConstruct

@Service
class Desert : WeatherCommand("desert", "Sets the desert boolean of the server.") {

    @PostConstruct
    fun configureOptions() {
        addOption(OptionType.BOOLEAN, "desert", "whether the region is a desert", true)
    }

    override val sudo = true

    override fun execute(event: SlashCommandEvent, client: Client): String {
        val desert = event.getOption("desert")!!.asBoolean
        client.config.desert = desert
        return "Desert set to $desert."
    }
}
