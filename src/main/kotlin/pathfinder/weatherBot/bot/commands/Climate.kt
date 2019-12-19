package pathfinder.weatherBot.bot.commands

import pathfinder.weatherBot.bot.CommandHandler

class Climate(handler: CommandHandler) : Command(handler) {
    override val command = "climate"
    override val description = "Sets the climate of the server."

    override fun execute() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun execute(params: List<String>) {
        if (params.isEmpty()) execute()
        else handler.bot?.setClimate(params.first())
    }

    override fun help() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}