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
import pathfinder.weatherBot.bot.interaction.CommandHandler
import pathfinder.weatherBot.location.Climate
import pathfinder.weatherBot.location.Elevation
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.time.Clock
import java.util.*

class Bot_old(private val guildId: String) {
    private val commandHandler = CommandHandler(this)
    private val guild: Guild?
        get() = botUser.getGuildById(guildId)
    private var outputChannelId = guild?.defaultChannel?.id
    private val outputChannel: TextChannel?
        get() = guild?.getTextChannelById(outputChannelId ?: "")
    val location = Location()
    internal val clock: Clock by lazy { Clock(this) }

    companion object : ListenerAdapter() {
        private lateinit var token: String
        private val instances = HashMap<String, Bot_old>()
        private val logger = LoggerFactory.getLogger(Bot_old::class.java)
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
            with(event.message) {
                logger.info("Received message:\n$author: $contentRaw in $guild#$channel")
                instances.getOrPut(guild.id, { Bot_old(guild.id) }).command(this)
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

    private fun command(message: Message) {
        commandHandler(message)?.let { post(it, message.textChannel) }
    }

    internal fun setChannel(channel: TextChannel, oldChannel: TextChannel = channel) = channel.run {
        outputChannelId = id
        post("Output channel has been set to $asMention.", oldChannel)
    }

    internal fun setClimate(message: String) =
            try {
                Climate.valueOf(message).let {
                    location.climate = it
                    "Climate set to $it."
                }
            } catch (e: IllegalArgumentException) {
                "Invalid climate type."
            }

    internal fun setElevation(message: String) =
            try {
                Elevation.valueOf(message).let {
                    location.elevation = it
                    "Elevation set to $it."
                }
            } catch (e: IllegalArgumentException) {
                "Invalid elevation type."
            }

    internal fun setDesert(message: String) =
            try {
                message.toBoolean().let {
                    location.desert = it
                    "Desert set to $it."
                }
            } catch (e: java.lang.IllegalArgumentException) {
                "Invalid parameter. Desert must be either TRUE or FALSE. "
            }

    internal fun start() = location.run {
        if (isSet()) clock.start()
        else "You first need to set the ${missing()}."
    }

    internal fun stop() = if ((::clock.getDelegate() as Lazy<*>).isInitialized()) clock.stop() else ""

    fun post(message: String, channel: TextChannel? = outputChannel) = channel?.sendMessage(message)?.queue()
}