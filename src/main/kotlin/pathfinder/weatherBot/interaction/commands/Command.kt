package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.Permission.ADMINISTRATOR
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.requests.restaction.MessageAction
import pathfinder.weatherBot.interaction.CommandHandler

abstract class Command(val handler: CommandHandler) {
    abstract val command: String
    abstract val description: String
    abstract val supportedParameterCounts: Array<Int>
    open val sudo = false

    internal abstract fun execute(message: Message): MessageAction
    abstract fun help(message: Message): MessageAction

    internal fun evaluate(message: Message) = when {
        sudo && !message.sudo -> message.channel.sendMessage("Only administrators may use this command.")
        message.params.count() !in supportedParameterCounts -> message.channel.sendMessage("Unacceptable parameters")
        else -> execute(message)
    }

    private val Message.sudo: Boolean
        get() = member?.hasPermission(ADMINISTRATOR) ?: false

    internal val Message.params: List<String>
        get() = contentRaw.substringAfter(command).removePrefix(" ").split(' ')
}