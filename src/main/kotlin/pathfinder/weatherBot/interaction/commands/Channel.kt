package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.requests.restaction.MessageAction
import pathfinder.weatherBot.interaction.CommandHandler

class Channel(handler: CommandHandler) : Command(handler) {
    override val command = "channel"
    override val description = "Sets the output channel."
    override val supportedParameterCounts = listOf(0, 1)
    override val sudo = true

    override fun execute(message: Message): MessageAction {
        val channel = if (message.params.isEmpty()) message.textChannel
        else message.mentionedChannels.firstOrNull()
            ?: return message.channel.sendMessage("The channel has to be mentioned first.")
        handler.client.channelId = channel.idLong
        return message.textChannel.sendMessage("Output channel set to ${channel.asMention}.")
    }

    override fun help(message: Message) =
        message.channel.sendMessage("Sets the output channel to current or mentioned channel.")
}