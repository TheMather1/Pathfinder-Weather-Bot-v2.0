package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyAction
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client

@Service
class Desert : Command{
    override val commandData = CommandData("desert", "Sets the desert boolean of the server.")
        .addOption(OptionType.BOOLEAN, "desert", "whether the region is a desert", true)
    override val sudo = true

    override fun execute(event: SlashCommandEvent, client: Client): ReplyAction {
        val desert = event.getOption("desert")!!.asBoolean
        client.config.desert = desert
        return event.reply("Desert set to $desert.")
    }
}