package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.entities.Message
import pathfinder.weatherBot.interaction.CommandHandler

class Start(handler: CommandHandler) : Command(handler) {
    override val command = "start"
    override val description = "Starts the bot."
    override val supportedParameterCounts = listOf(0)
    override val sudo = true

    override fun execute(message: Message) = message.channel.sendMessage(handler.client.clock.start())

    override fun help(message: Message) = message.channel.sendMessage("Starts the bot.")
}