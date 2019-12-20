package pathfinder.weatherBot.bot.interaction.commands

import pathfinder.weatherBot.bot.interaction.CommandHandler

class Prefix(handler: CommandHandler) : Command(handler) {
    override val command = "prefix"
    override val description = "Sets the bot prefix."
    override val supportedParameterCounts = arrayOf(1)
    override val sudo = true

    override fun execute() {
        TODO("Not supported.")
    }

    override fun execute(params: List<String>) {
        handler.prefix = params.first()
        TODO("Confirm change.")
    }

    override fun help() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}