package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.entities.Message
import pathfinder.weatherBot.interaction.CommandHandler
import pathfinder.weatherBot.location.Elevation

class SetElevation(handler: CommandHandler) : Command(handler) {
    override val command = "elevation"
    override val description = "Sets the elevation of the server."
    override val supportedParameterCounts = listOf(1)
    override val sudo = true

    override fun execute(message: Message) = try {
        handler.client.biome.elevation = Elevation.valueOf(message.params.first())
        message.channel.sendMessage("Elevation has been set to ${handler.client.biome.elevation}.")
    } catch (_: Throwable) {
        message.channel.sendMessage("That is not a supported elevation.")
    }

    override fun help(message: Message) =
        message.channel.sendMessage("Sets elevation of area to SEA_LEVEL, LOWLAND, HIGHLAND, HIGHLAND_ARID, or HIGHLAND_MOUNTAIN")
}