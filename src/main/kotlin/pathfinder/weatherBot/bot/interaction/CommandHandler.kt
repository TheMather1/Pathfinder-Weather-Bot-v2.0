package pathfinder.weatherBot.bot.interaction

import net.dv8tion.jda.api.Permission.ADMINISTRATOR
import net.dv8tion.jda.api.entities.Message
import pathfinder.weatherBot.bot.interaction.commands.*
import pathfinder.weatherBot.removeSuffixOrNull
import java.lang.String.CASE_INSENSITIVE_ORDER

class CommandHandler(internal val client: Client) {
    internal val commands = listOf(
            Help(this),
            Start(this),
            Stop(this),
            SetClimate(this),
            SetElevation(this),
            Desert(this),
            Prefix(this),
            Channel(this),
            Status(this)
    ).associateBy(Command::command)
            .toSortedMap(CASE_INSENSITIVE_ORDER)

    internal operator fun invoke(message: Message): String? {
        val text = message.contentRaw.removeSuffixOrNull(client.prefix, true) ?: return null
        val sudo = message.member?.hasPermission(ADMINISTRATOR) ?: false
        val newChannel = message.mentionedChannels.firstOrNull()

        UserInput(text, sudo, this).execute(message.textChannel, newChannel)
        return null
    }
}