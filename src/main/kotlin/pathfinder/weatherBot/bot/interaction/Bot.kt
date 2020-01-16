package pathfinder.weatherBot.bot.interaction

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.guild.GuildBanEvent
import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.slf4j.LoggerFactory
import pathfinder.weatherBot.bot.Bot_old
import java.util.*

object Bot : ListenerAdapter() {
    private lateinit var token: String
    private val instances = HashMap<String, Bot_old>()
    private val logger = LoggerFactory.getLogger(Bot.javaClass)
    internal lateinit var botUser: JDA

    @JvmStatic
    fun main(args: Array<String>) {
        token = args[0]
        botUser = JDABuilder(token)
                .setActivity(Activity.watching("the skies"))
                .build()
        botUser.addEventListener(this)
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        with(event.message) {
            logger.info("Received message:\n$author: $contentRaw in $guild#$channel")
            instances.getOrPut(guild.id, { Bot_old(guild.id) })
        }
    }

    override fun onGuildJoin(event: GuildJoinEvent) {
        event.guild.id.also { instances[it] = Bot_old(it) }
    }

    override fun onGuildLeave(event: GuildLeaveEvent) {
        instances.remove(event.guild.id)?.stop()
    }

    override fun onGuildBan(event: GuildBanEvent) {
        instances.remove(event.guild.id)?.stop()
    }
}