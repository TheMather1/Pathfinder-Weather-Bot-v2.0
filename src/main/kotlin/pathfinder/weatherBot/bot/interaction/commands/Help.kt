package pathfinder.weatherBot.bot.interaction.commands

import pathfinder.weatherBot.bot.interaction.CommandHandler


class Help(handler: CommandHandler) : Command(handler = handler) {
    override val command = "help"
    override val description = "List the available commands."
    override val supportedParameterCounts = arrayOf(0, 1)

    override fun execute() {
        handler.client.messageHandler.post("""
            Available commands:
            ${handler.commands.values.joinToString("\n") { "${it.command} - ${it.description}" }}
        """.trimIndent())
    }

    override fun execute(params: List<String>) {
        if (params.isEmpty()) execute()
        else handler.commands[params.first()]?.help()
    }

    override fun help() {
        TODO("Help text for help.")
    }
}