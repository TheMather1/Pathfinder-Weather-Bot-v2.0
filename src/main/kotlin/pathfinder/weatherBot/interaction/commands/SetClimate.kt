package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.requests.restaction.MessageAction
import pathfinder.weatherBot.interaction.CommandHandler
import pathfinder.weatherBot.location.Climate

class SetClimate(handler: CommandHandler) : Command(handler) {
    override val command = "climate"
    override val description = "Sets the climate of the server."
    override val supportedParameterCounts = arrayOf(1)
    override val sudo = true

    override fun execute(message: Message): MessageAction {
        handler.client.biome.climate = Climate.valueOf(message.params.first())
        message.channel.sendMessage("Climate has been altered!")
    }

    override fun help(message: Message): MessageAction {
        message.channel.sendMessage("Sets the climate to COLD, TEMPERATE, or TROPICAL.")
    }
}