package pathfinder.weatherBot.event

import net.dv8tion.jda.api.events.message.GenericMessageEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client

@Service
class EventService(
    private val registrations: MutableMap<Long, Client>
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun validCommand(event: MessageReceivedEvent) = event.message.contentRaw.startsWith(event.client.prefix, true)

    fun executeCommand(event: MessageReceivedEvent) = try {
        event.client.commandHandler(event.message)
    } catch (e: Throwable) {
        logger.error("Caught exception while executing command ${event.message.contentRaw}:", e)
        event.channel.sendMessage("An error occurred while handling your command. Details have been logged.")
    }

    private val GenericMessageEvent.client: Client
        get() = registrations.getOrPut(guild.idLong) { Client(guild) }
}