package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.requests.restaction.MessageAction
import pathfinder.weatherBot.interaction.CommandHandler

class Prefix(handler: CommandHandler) : Command(handler) {
    override val command = "prefix"
    override val description = "Sets the bot prefix."
    override val supportedParameterCounts = arrayOf(1)
    override val sudo = true

    override fun execute(message: Message): MessageAction {
        handler.client.prefix = message.params.first()
        message.channel.sendMessage("Prefix has been changed!")
    }

    override fun help(message: Message): MessageAction {
        message.channel.sendMessage("Allows the prefix the bot uses to be changed for compatibility purposes.")
    }
}