package pathfinder.weatherBot.bot.interaction

import net.dv8tion.jda.api.entities.TextChannel

class MessageHandler(private val client: Client) {
    fun post(message: String) {
        client.outputChannel.sendMessage(message)
    }

    fun post(message: String, channel: TextChannel) {
        channel.sendMessage(message)
    }
}