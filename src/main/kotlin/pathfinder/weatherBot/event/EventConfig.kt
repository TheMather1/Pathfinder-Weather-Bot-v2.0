package pathfinder.weatherBot.event

import club.minnced.jda.reactor.ReactiveEventManager
import club.minnced.jda.reactor.asMono
import club.minnced.jda.reactor.on
import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventConfig(private val eventService: EventService) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Bean
    fun eventManager() = ReactiveEventManager().apply {
        on<ReadyEvent>()
            .subscribe { logger.info("Bot online.") }

        on<MessageReceivedEvent>()
            .filter { it.isTextMessage() }
            .doOnEach { it.get()?.log() }
            .filter(eventService::validCommand)
            .map(eventService::executeCommand)
            .subscribe()
    }

    private fun MessageReceivedEvent.isTextMessage() = isFromType(ChannelType.TEXT)

    private fun MessageReceivedEvent.log() {
        logger.info("Received message:\n${author.name}: ${message.contentRaw} in ${guild.name}#${channel.name}")
    }

}