package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.requests.restaction.MessageAction
import pathfinder.weatherBot.interaction.CommandHandler

class Desert(handler: CommandHandler) : Command(handler) {
    override val command = "desert"
    override val description = "Sets the desert boolean of the server."
    override val supportedParameterCounts = listOf(1)
    override val sudo = true

    override fun execute(message: Message): MessageAction {
        val desert = message.params.first().toBoolean()
        handler.client.biome.desert = desert
        return message.channel.sendMessage("Desert set to $desert.")
    }

    override fun help(message: Message) =
        message.channel.sendMessage("If set to true, area is treated as a desert.")
}