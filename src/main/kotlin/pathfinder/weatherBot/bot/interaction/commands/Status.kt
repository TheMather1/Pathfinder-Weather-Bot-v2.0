package pathfinder.weatherBot.bot.interaction.commands

import pathfinder.weatherBot.bot.interaction.CommandHandler
import javax.naming.OperationNotSupportedException

class Status(handler: CommandHandler) : Command(handler) {
    override val command: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val description: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val supportedParameterCounts: Array<Int>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun execute() {
        handler.client.messageHandler.post("The bot is ${handler.client.clock.status}", channel)
    }

    override fun execute(params: List<String>) {
        throw OperationNotSupportedException()
    }

    override fun help() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}