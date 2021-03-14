package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.requests.restaction.MessageAction
import pathfinder.weatherBot.interaction.CommandHandler
import pathfinder.weatherBot.location.Elevation

class SetElevation(handler: CommandHandler) : Command(handler) {
    override val command = "elevation"
    override val description = "Sets the elevation of the server."
    override val supportedParameterCounts = arrayOf(1)
    override val sudo = true

    override fun execute(message: Message): MessageAction {
        handler.client.biome.elevation = Elevation.valueOf(message.params.first())
        TODO("Confirm change.")
    }

    override fun help(message: Message): MessageAction {
        TODO("not implemented")
    }
}