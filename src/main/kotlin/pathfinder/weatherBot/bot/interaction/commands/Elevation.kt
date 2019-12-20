package pathfinder.weatherBot.bot.interaction.commands

import pathfinder.weatherBot.bot.interaction.CommandHandler

class Elevation(handler: CommandHandler) : Command(handler) {
    override val command = "elevation"
    override val description = "Sets the elevation of the server."
    override val supportedParameterCounts = arrayOf(1)
    override val sudo = true

    override fun execute() {
        throw UnsupportedOperationException()
    }

    override fun execute(params: List<String>) {
        if (params.size == 1) handler.bot?.setElevation(params.first())
        else throw UnsupportedOperationException()
    }

    override fun help() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}