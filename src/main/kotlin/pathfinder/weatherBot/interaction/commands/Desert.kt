package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client

@Service
class Desert : WeatherCommand{
    override val commandData = CommandData("desert", "Sets the desert boolean of the server.")
        .addOption(OptionType.BOOLEAN, "desert", "whether the region is a desert", true)
    override val sudo = true

    override fun execute(event: SlashCommandEvent, client: Client): String {
        val desert = event.getOption("desert")!!.asBoolean
        client.config.desert = desert
        return "Desert set to $desert."
    }
}