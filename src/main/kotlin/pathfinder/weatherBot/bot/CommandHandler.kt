package pathfinder.weatherBot.bot

import net.dv8tion.jda.api.Permission.ADMINISTRATOR
import net.dv8tion.jda.api.entities.Message
import pathfinder.weatherBot.bot.commands.*
import java.lang.String.CASE_INSENSITIVE_ORDER
import kotlin.reflect.KFunction1

class CommandHandler(internal val bot: Bot?) {

    companion object {
        private const val TOO_MANY_PARAMETERS = "This command does not take any parameters."
    }

    internal val commands = listOf(
            Help(this)
    ).associateBy(Command::command)
            .toSortedMap(CASE_INSENSITIVE_ORDER)

    internal var prefix = "WEATHER!"

    private fun String.firstWord() = substringBefore(' ', this)
    private fun String.succeedingWords() = substringAfter(' ').takeUnless { none(' '::equals) }
    private fun String?.assertNoMoreParams(function: () -> String?) = if (isNullOrEmpty() && function != null) function() else TOO_MANY_PARAMETERS

    internal operator fun invoke(message: Message): String? {
        fun sudo(function: () -> String?) = if (message.member?.hasPermission(ADMINISTRATOR) == true) function() else "You do not have permission to do this."
        fun String.sudo(function: KFunction1<@ParameterName(name = "param") String?, String?>) = sudo { function(succeedingWords()) }
        fun Message.sudo(function: ((Message) -> String)?) = sudo { function?.invoke(this) }
        val command = message.contentRaw.toUpperCase().takeIf { it.startsWith(prefix) }?.removePrefix(prefix)
                ?: return null


        with(command.split(' ')) {
            commands[first()].also {
                when {
                    it == null -> bot?.post(message = "$command is not a valid command.")
                    size > 1 -> it.execute(drop(1))
                    else -> it.execute()
                }
            }
        }
        if (bot == null) return null

        return when (command.firstWord()) {
            "HELP" -> help(command.succeedingWords())
            "STATUS" -> command.succeedingWords().assertNoMoreParams { "The bot is currently ${bot.clock.status}" }
            "START" -> command.sudo(::start)
            "STOP" -> command.sudo(::stop)
            "CLIMATE" -> command.sudo(::climate)
            "ELEVATION" -> command.sudo(::elevation)
            "DESERT" -> command.sudo(::desert)
            "PREFIX" -> command.sudo(::prefix)
            "CHANNEL" -> message.sudo(bot::setChannel)
            else -> "That is not a valid command. Try `${prefix}help` to list the available commands."
        }
    }

    private fun help(vararg command: String?) = when (command.firstOrNull()) {
        null -> listCommands()
        "HELP" -> helpDescription()
        "STATUS" -> statusDescription()
        "START" -> startDescription()
        "STOP" -> stopDescription()
        "CLIMATE" -> climateDescription()
        "ELEVATION" -> elevationDescription()
        "DESERT" -> desertDescription()
        "PREFIX" -> prefixDescription()
        "CHANNEL" -> channelDescription()
        else -> "That is not a valid command."
    }

    private fun helpDescription() = """|Command: help
    |Usage:
    |   help - List the available commands.
    |   help [command] - Describe the specified command.
    """.trimMargin()

    private fun listCommands() = """List of commands:
    |   help - This command.
    |   TODO
    """.trimMargin()

    private fun statusDescription(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun startDescription(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stopDescription(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun climateDescription(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun elevationDescription(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun desertDescription(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun prefixDescription(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun channelDescription(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun start(param: String?) = param.assertNoMoreParams { bot?.start() }
    private fun stop(param: String?) = param.assertNoMoreParams { bot?.stop() }

    private fun climate(param: String?) = bot?.setClimate(param.orEmpty())
    private fun elevation(param: String?) = bot?.setElevation(param.orEmpty())
    private fun desert(param: String?) = bot?.setDesert(param.orEmpty())

    private fun prefix(param: String?) = param.takeUnless(String?::isNullOrBlank)?.run {
        succeedingWords().assertNoMoreParams {
            prefix = firstWord()
            "Prefix has been set to ${firstWord()}"
        }
    } ?: "No prefix specified."
}