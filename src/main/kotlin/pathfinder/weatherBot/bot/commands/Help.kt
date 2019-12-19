package pathfinder.weatherBot.bot.commands

import pathfinder.weatherBot.bot.CommandHandler


class Help(handler: CommandHandler) : Command(handler = handler) {
    override val command = "help"
    override val description = "List the available commands."

    override fun execute() {
        handler.bot?.post("""
            Available commands:
            ${handler.commands.values.map { "${it.command} - ${it.description}" }.joinToString("\n")}
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