package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.entities.Message
import pathfinder.weatherBot.interaction.CommandHandler

class Stop(handler: CommandHandler) : Command(handler) {
    override val command = "stop"
    override val description = "Stops the bot."
    override val supportedParameterCounts = listOf(0)
    override val sudo = true

    override fun execute(message: Message) = message.channel.sendMessage(handler.client.clock.stop())

    override fun help(message: Message) = message.channel.sendMessage("Puts an end to the bot.")
}