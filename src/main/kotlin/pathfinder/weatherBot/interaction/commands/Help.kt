package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.entities.Message
import pathfinder.weatherBot.interaction.CommandHandler


class Help(handler: CommandHandler) : Command(handler = handler) {
    override val command = "help"
    override val description = "List the available commands."
    override val supportedParameterCounts = listOf(0, 1)

    override fun execute(message: Message) =
        if (message.params.isEmpty()) listCommands(message)
        else handler.commands[message.params.first()]?.help(message)
            ?: message.channel.sendMessage("No such command.")

    private fun listCommands(message: Message) = message.channel.sendMessage(
        "Available commands:\n"
                + handler.commands.values.joinToString("\n") { "${it.command} - ${it.description}" }
    )

    override fun help(message: Message) =
        message.channel.sendMessage("Do what you just did but with a different command.")
}