package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.requests.restaction.MessageAction
import pathfinder.weatherBot.interaction.CommandHandler

class Channel(handler: CommandHandler) : Command(handler) {
    override val command = "channel"
    override val description = "Sets the output channel."
    override val supportedParameterCounts = arrayOf(0, 1)
    override val sudo = true

    override fun execute(message: Message): MessageAction {
        val channel = if (message.params.isEmpty()) message.textChannel
        else message.mentionedChannels.firstOrNull()
            ?: TODO("Channel not recognized.")
        handler.client.outputChannel = channel
        return message.textChannel.sendMessage("Output channel set to ${channel.asMention}")
    }

    override fun help(message: Message): MessageAction {
        TODO("not implemented")
    }
}