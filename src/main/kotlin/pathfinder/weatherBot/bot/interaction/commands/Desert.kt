package pathfinder.weatherBot.bot.interaction.commands

import pathfinder.weatherBot.bot.interaction.CommandHandler

class Desert(handler: CommandHandler) : Command(handler) {
    override val command = "desert"
    override val description = "Sets the desert boolean of the server."
    override val supportedParameterCounts = arrayOf(1)
    override val sudo = true

    override fun execute() {
        //Illegal function.
    }

    override fun execute(params: List<String>) {
        handler.client.biome.desert = params.first().toBoolean()
    }

    override fun help() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}