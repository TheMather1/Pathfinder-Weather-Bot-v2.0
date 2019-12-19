package pathfinder.weatherBot.bot.commands

import pathfinder.weatherBot.bot.CommandHandler

abstract class Command(val handler: CommandHandler) {

    abstract val command: String
    abstract val description: String

    abstract fun execute()
    abstract fun execute(params: List<String>)
    abstract fun help()
}