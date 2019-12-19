package pathfinder.weatherBot.bot.commands

import pathfinder.weatherBot.bot.CommandHandler

class Stop(handler: CommandHandler) : Command(handler) {
    override val command = "stop"
    override val description = "Stops the bot."

    override fun execute() {
        handler.bot?.stop()
    }

    override fun execute(params: List<String>) {
        if (params.isEmpty()) execute()
        else TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun help() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}