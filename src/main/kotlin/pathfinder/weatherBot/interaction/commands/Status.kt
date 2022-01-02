package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.entities.Message
import pathfinder.weatherBot.interaction.CommandHandler

class Status(handler: CommandHandler) : Command(handler) {
    override val command = "status"
    override val description = "Checks bot status."
    override val supportedParameterCounts = listOf(0)

    override fun execute(message: Message) = message.channel.sendMessage("The bot is ${handler.client.clock.status}.")

    override fun help(message: Message) = message.channel.sendMessage("Checks the status of the bot.")
}