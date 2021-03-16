package pathfinder.weatherBot

import club.minnced.jda.reactor.ReactiveEventManager
import club.minnced.jda.reactor.asMono
import club.minnced.jda.reactor.on
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.ChannelType
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import org.slf4j.LoggerFactory
import pathfinder.weatherBot.interaction.Client
import java.util.*

object Bot {
    private lateinit var token: String
    private val instances = HashMap<String, Client>()
    private val logger = LoggerFactory.getLogger(Bot.javaClass)
    internal lateinit var botUser: JDA

    private val eventManager = ReactiveEventManager().apply {
        on<ReadyEvent>()
            .next()
            .subscribe { logger.info("Bot online.") }

        on<MessageReceivedEvent>()
            .map { it.message }
            .filter { it.isFromType(ChannelType.TEXT) }
            .doOnEach {
                it.get()?.apply {
                    logger.info("Received message:\n${author.name}: $contentRaw in ${guild.name}#${channel.name}")
                }
            }
            .filter { it.contentRaw.startsWith(it.client.prefix, true) }
            .map {
                try {
                    it.client.commandHandler(it)
                } catch (e: Throwable) {
                    logger.error("Caught exception while executing command ${it.contentRaw}:", e)
                    it.channel.sendMessage("An error occurred while handling your command. Details have been logged.")
                }
            }
            .flatMap { it.asMono() }
            .subscribe()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        token = args[0]
        botUser = JDABuilder.createDefault(token)
            .setActivity(Activity.watching("the skies"))
            .setEventManager(eventManager)
            .build()
    }

    private val Message.client: Client
        get() = instances.getOrPut(guild.id, clientProvider)

    private val Message.clientProvider: () -> Client
        get() = { Client(guild.id, guild.defaultChannel!!.id) }
}