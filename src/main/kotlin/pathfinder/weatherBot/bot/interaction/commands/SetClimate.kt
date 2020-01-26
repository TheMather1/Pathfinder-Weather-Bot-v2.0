package pathfinder.weatherBot.bot.interaction.commands

import pathfinder.weatherBot.bot.interaction.CommandHandler
import pathfinder.weatherBot.location.Climate

class SetClimate(handler: CommandHandler) : Command(handler) {
    override val command = "climate"
    override val description = "Sets the climate of the server."
    override val supportedParameterCounts = arrayOf(1)

    override fun execute() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun execute(params: List<String>) {
        if (params.isEmpty()) execute()
        else handler.client.biome.climate = Climate.valueOf(params.first())
    }

    override fun help() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}