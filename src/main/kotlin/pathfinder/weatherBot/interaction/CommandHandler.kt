package pathfinder.weatherBot.interaction

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.requests.restaction.MessageAction
import pathfinder.weatherBot.interaction.commands.*
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

    internal operator fun invoke(message: Message): MessageAction {
        val command = message.contentRaw.removePrefix(client.prefix).substringBefore(' ')
        return commands[command]?.evaluate(message) ?: message.channel.sendMessage("Command does not exist.")
    }
}