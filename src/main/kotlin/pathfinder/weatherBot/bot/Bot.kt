package pathfinder.weatherBot.bot

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity.watching
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.events.guild.GuildBanEvent
import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.slf4j.LoggerFactory
import pathfinder.weatherBot.location.Climate
import pathfinder.weatherBot.location.Elevation
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.time.Clock
import java.util.*

class Bot(private val guildId: String){
    private val commandHandler = CommandHandler(this)
    private val guild: Guild?
        get() = botUser.getGuildById(guildId)
    private var outputChannelId = guild?.defaultChannel?.id
    private val outputChannel: TextChannel?
        get() = guild?.getTextChannelById(outputChannelId ?: "")
    val location = Location()
    internal val clock: Clock by lazy { Clock(this) }

    companion object: ListenerAdapter() {
        private lateinit var token: String
        private val instances = HashMap<String, Bot>()
        private val logger = LoggerFactory.getLogger(Bot::class.java)
        lateinit var botUser: JDA

        @JvmStatic
        fun main(args: Array<String>) {
            token = args[0]
            botUser = JDABuilder(token)
                    .setActivity(watching("the weather"))
                    .build()
            botUser.addEventListener(this)
        }

        override fun onMessageReceived(event: MessageReceivedEvent) {
            event.message.run {
                logger.info("Received message:\n$author: $contentRaw in $guild#$channel")
                instances.getOrPut(guild.id, { Bot(guild.id) }).command(this)
            }
        }

        override fun onGuildJoin(event: GuildJoinEvent) {
            event.guild.id.also { instances[it] = Bot(it) }
        }

        override fun onGuildLeave(event: GuildLeaveEvent){
            instances.remove(event.guild.id)?.stop()
        }

        override fun onGuildBan(event: GuildBanEvent){
            instances.remove(event.guild.id)?.stop()
        }
    }

    private fun command(message: Message) {
        commandHandler(message)?.let{ post(message.textChannel, it) }
    }

    internal fun setChannel(message: Message): String{
        val split = message.contentRaw.split(' ')
        return message.mentionedChannels[0]
                .takeIf { split.size == 2 && split[1] == it.asMention}
                ?.run {
                    outputChannelId = id
                    "Output channel has been set to $asMention."
                }
                ?: "Cannot recognize channel. Correct usage: channel [#channel]"
    }

    internal fun start(): String = if (location.isSet()) clock.start()
            else "You first need to set the ${location.missing()}."
    internal fun stop(): String = if ((::clock.getDelegate() as Lazy<*>).isInitialized()) clock.stop() else ""

    internal fun setClimate(message: String): String =
        try {
            Climate.valueOf(message.toUpperCase()).let {
                location.climate = it
                "Climate set to $it."
            }
        } catch (e: IllegalArgumentException) { "Invalid climate type." }

    internal fun setElevation(message: String): String =
        try {
            Elevation.valueOf(message.toUpperCase()).let {
                location.elevation = it
                "Elevation set to $it."
            }
        } catch (e: IllegalArgumentException) { "Invalid elevation type." }

    internal fun setDesert(message: String): String =
            try {
                message.toBoolean().let {
                    location.desert = it
                    "Desert set to $it."
                }
            } catch (e: java.lang.IllegalArgumentException) { "Invalid parameter. Desert must be either TRUE or FALSE. "}

    fun post(channel: TextChannel? = null, message: String) = (channel ?: outputChannel)?.sendMessage(message)?.complete()
}