package pathfinder.weatherBot.bot.interaction.commands

import net.dv8tion.jda.api.entities.TextChannel
import pathfinder.weatherBot.bot.interaction.CommandHandler

abstract class Command(val handler: CommandHandler) {
    abstract val command: String
    abstract val description: String
    abstract val supportedParameterCounts: Array<Int>
    lateinit var channel: TextChannel
    open val sudo = false

    protected abstract fun execute()
    protected abstract fun execute(params: List<String>)
    abstract fun help()

    internal fun evaluate(params: List<String>, sudo: Boolean) {
        when{
            this.sudo && !sudo -> throw IllegalAccessError()
            params.size !in supportedParameterCounts -> throw UnsupportedOperationException()
            params.isEmpty() -> execute()
            else -> execute(params)
        }
    }
}