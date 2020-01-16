package pathfinder.weatherBot.bot.interaction

import net.dv8tion.jda.api.Permission.ADMINISTRATOR
import net.dv8tion.jda.api.entities.Message
import pathfinder.weatherBot.bot.interaction.commands.*
import pathfinder.weatherBot.removeSuffixOrNull
import java.lang.String.CASE_INSENSITIVE_ORDER

class CommandHandler(private val client: Client) {

    internal var prefix = "WEATHER!"

    internal val commands = listOf(
            Help(this),
            Start(this),
            Stop(this),
            Climate(this),
            Elevation(this),
            Desert(this),
            Prefix(this),
            Channel(this)
    ).associateBy(Command::command)
            .toSortedMap(CASE_INSENSITIVE_ORDER)

    internal operator fun invoke(message: Message): String? {
        val text = message.contentRaw.removeSuffixOrNull(prefix, true) ?: return null
        val sudo = message.member?.hasPermission(ADMINISTRATOR) ?: false
        val newChannel = message.mentionedChannels.firstOrNull()

        UserInput(text, sudo, this).execute(message.textChannel, newChannel)
        return null
    }
}