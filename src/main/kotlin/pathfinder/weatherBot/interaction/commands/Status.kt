package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.requests.restaction.MessageAction
import pathfinder.weatherBot.interaction.CommandHandler

class Status(handler: CommandHandler) : Command(handler) {
    override val command = "status"
    override val description = TODO("not implemented")
    override val supportedParameterCounts = arrayOf(0)

    override fun execute(message: Message): MessageAction =
        message.channel.sendMessage("The bot is ${handler.client.clock.status}.")

    override fun help(message: Message): MessageAction {
        TODO("not implemented")
    }
}