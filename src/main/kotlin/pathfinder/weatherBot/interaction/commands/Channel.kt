package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyAction
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client

@Service
class Channel: Command {
    override val commandData = CommandData("channel", "Sets the output channel.")
        .addOption(OptionType.CHANNEL, "channel", "output channel")
    override val sudo = true

    override fun execute(event: SlashCommandEvent, client: Client): ReplyAction {
        val channel = event.getOption("channel")?.asGuildChannel ?: event.textChannel
        client.config.outputChannel = channel.idLong
        return event.reply("Output channel set to ${channel.asMention}.")
    }
}