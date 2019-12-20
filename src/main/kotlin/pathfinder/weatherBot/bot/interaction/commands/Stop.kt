package pathfinder.weatherBot.bot.interaction.commands

import pathfinder.weatherBot.bot.interaction.CommandHandler

class Stop(handler: CommandHandler) : Command(handler) {
    override val command = "stop"
    override val description = "Stops the bot."
    override val supportedParameterCounts = arrayOf(0)
    override val sudo = true

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