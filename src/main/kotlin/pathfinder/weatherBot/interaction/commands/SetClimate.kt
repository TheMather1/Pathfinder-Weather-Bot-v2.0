package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.entities.Message
import pathfinder.weatherBot.interaction.CommandHandler
import pathfinder.weatherBot.location.Climate

class SetClimate(handler: CommandHandler) : Command(handler) {
    override val command = "climate"
    override val description = "Sets the climate of the server."
    override val supportedParameterCounts = listOf(1)
    override val sudo = true

    override fun execute(message: Message) = try {
        handler.client.biome.climate = Climate.valueOf(message.params.first())
        message.channel.sendMessage("Climate has been set to ${handler.client.biome.climate}.")
    } catch (_: Throwable) {
        message.channel.sendMessage("That is not a supported climate.")
    }

    override fun help(message: Message) =
        message.channel.sendMessage("Sets the climate to COLD, TEMPERATE, or TROPICAL.")
}