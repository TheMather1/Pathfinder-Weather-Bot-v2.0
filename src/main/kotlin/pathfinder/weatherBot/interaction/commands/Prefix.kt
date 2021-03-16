package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.requests.restaction.MessageAction
import pathfinder.weatherBot.interaction.CommandHandler

class Prefix(handler: CommandHandler) : Command(handler) {
    override val command = "prefix"
    override val description = "Sets the bot prefix."
    override val supportedParameterCounts = listOf(1)
    override val sudo = true

    override fun execute(message: Message): MessageAction {
        handler.client.prefix = message.params.first()
        return message.channel.sendMessage("Prefix has been set to ${handler.client.prefix}.")
    }

    override fun help(message: Message) =
        message.channel.sendMessage("Allows the changing the prefix of bot commands.")
}