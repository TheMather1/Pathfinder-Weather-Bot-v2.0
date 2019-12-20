package pathfinder.weatherBot.bot.interaction.commands

import pathfinder.weatherBot.bot.interaction.CommandHandler

class Start(handler: CommandHandler) : Command(handler) {
    override val command = "start"
    override val description = "Starts the bot."
    override val supportedParameterCounts = arrayOf(0)
    override val sudo = true

    override fun execute() {
        handler.bot?.start()
    }

    override fun execute(params: List<String>) {
        TODO()
    }

    override fun help() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}