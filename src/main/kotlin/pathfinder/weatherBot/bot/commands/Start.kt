package pathfinder.weatherBot.bot.commands

import pathfinder.weatherBot.bot.CommandHandler

class Start(handler: CommandHandler) : Command(handler) {
    override val command = "start"
    override val description = "Starts the bot."

    override fun execute() {
        handler.bot?.start()
    }

    override fun execute(params: List<String>) {
        if (params.isEmpty()) execute()
        else TODO()
    }

    override fun help() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}