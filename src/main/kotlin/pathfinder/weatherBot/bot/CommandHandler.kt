package pathfinder.weatherBot.bot

import net.dv8tion.jda.api.Permission.ADMINISTRATOR
import net.dv8tion.jda.api.entities.Message

class CommandHandler(private val bot: Bot) {

    companion object {
        private const val TOO_MANY_PARAMETERS = "This command does not take any parameters."
    }

    private var prefix = "WEATHER!"

    private fun String.firstWord() : String = substringBefore(' ', this)
    private fun String.succeedingWords() : String? = substringAfter(' ').takeUnless{ none(' '::equals) }
    private fun String?.assertNoMoreParams(function: () -> String) : String = if(isNullOrEmpty()) function() else TOO_MANY_PARAMETERS

    internal operator fun invoke(message : Message) : String? {
        fun sudo(function: ()->String) = if(message.member?.hasPermission(ADMINISTRATOR) == true) function() else "You do not have permission to do this."
        fun String.sudo(function: (String?)->String) = sudo { function(succeedingWords()) }
        fun Message.sudo(function: (Message)->String) = sudo { function(this) }
        val command = message.contentRaw.toUpperCase().takeIf { it.startsWith(prefix) }?.removePrefix(prefix) ?: return null

        return when (command.firstWord()) {
            "HELP" -> help(command.succeedingWords())
            "STATUS" -> command.succeedingWords().assertNoMoreParams { "The bot is currently ${bot.clock.status()}" }
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

    private fun help(vararg command: String?): String {
        return when (command.firstOrNull()) {
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
    }

    private fun helpDescription(): String = """|Command: help
    |Usage:
    |   help - List the available commands.
    |   help [command] - Describe the specified command.
    """.trimMargin()

    private fun listCommands(): String = """List of commands:
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

    private fun start(param : String?) : String = param.assertNoMoreParams(bot::start)
    private fun stop(param : String?) : String = param.assertNoMoreParams(bot::stop)

    private fun climate(param : String?) : String = bot.setClimate(param.orEmpty())
    private fun elevation(param : String?) : String = bot.setElevation(param.orEmpty())
    private fun desert(param : String?) : String = bot.setDesert(param.orEmpty())

    private fun prefix(param: String?) : String = param.takeUnless(String?::isNullOrBlank)?.let {
        it.succeedingWords().assertNoMoreParams {
            prefix = it.firstWord()
            "Prefix has been set to ${it.firstWord()}"
        }
    } ?: "No prefix specified."
}