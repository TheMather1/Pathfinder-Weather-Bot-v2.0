package pathfinder.weatherBot

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.Permission
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
    private var prefix = DEFAULT_PREFIX
    private val guild: Guild?
        get() = botUser.getGuildById(guildId)
    private var outputChannelId = guild?.defaultChannel?.id
    private val outputChannel: TextChannel?
        get() = guild?.getTextChannelById(outputChannelId ?: "")
    val location = Location()
    private val clock: Clock by lazy { Clock(this) }

    companion object: ListenerAdapter() {
        private const val DEFAULT_PREFIX = "w!"
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
                instances.getOrPut(guild.id, {Bot(guild.id)}).takeIf {
                    contentRaw.startsWith(it.prefix)
                }?.let {
                    it.post(this.textChannel, it.command(this))
                }
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

        private fun helpText(message: String): String = when(message){
            "" -> listCommands()
            "channel" -> "Usage: channel [#channel] - Redirects weather output to the specified channel."
            else -> "Unrecognized command - $message."
        }

        private fun listCommands(): String = "help - You're looking at it. List available commands.\n" +
                "help [command] - Display more information on the command."
    }

    private fun command(message: Message): String{
        message.apply {
            val command = contentRaw.substringAfter(prefix).substringBefore(" ").toLowerCase()
            val sudo = guild.getMember(author)!!.hasPermission(Permission.ADMINISTRATOR)
            val param = contentRaw.substringAfter(command).substringAfter(' ')
            return when (command){
                "help" -> helpText(param)
                "start" -> if(sudo) start() else "You do not have permission to start the bot."
                "stop" -> if(sudo) stop() else "You do not have permission to stop the bot."
                "status" -> "The bot is currently ${clock.status()}"
                "channel" -> if (sudo) setChannel(this) else "You do not have permission to change the output channel."
                "climate" -> if (sudo) setClimate(param) else "You do not have permission to change the climate."
                "elevation" -> if (sudo) setElevation(param) else "You do not have permission to change the elevation."
                "desert" -> if (sudo) setDesert(param) else "You do not have permission to change the desert state."
                else -> "Unrecognized command - $command."
            }
        }
    }

    private fun setChannel(message: Message): String{
        val split = message.contentRaw.split(' ')
        return message.mentionedChannels[0]
                .takeIf { split.size == 2 && split[1] == it.asMention}
                ?.run {
                    outputChannelId = id
                    "Output channel has been set to $asMention."
                }
                ?: "Cannot recognize channel. Correct usage: channel [#channel]"
    }

    private fun start(): String = if (location.isSet()) clock.start()
            else "You first need to set the ${location.missing()}."
    private fun stop(): String = if ((::clock.getDelegate() as Lazy<*>).isInitialized()) clock.stop() else ""

    private fun setClimate(message: String): String =
        try {
            Climate.valueOf(message.toUpperCase()).let {
                location.climate = it
                "Climate set to $it."
            }
        } catch (e: IllegalArgumentException) { "Invalid climate type." }

    private fun setElevation(message: String): String =
        try {
            Elevation.valueOf(message.toUpperCase()).let {
                location.elevation = it
                "Elevation set to $it."
            }
        } catch (e: IllegalArgumentException) { "Invalid elevation type." }

    private fun setDesert(message: String): String =
            try {
                message.toBoolean().let {
                    location.desert = it
                    "Desert set to $it."
                }
            } catch (e: java.lang.IllegalArgumentException) { "Invalid parameter. Desert must be either TRUE or FALSE. "}

    fun post(message: String) = outputChannel?.sendMessage(message)?.complete()
    fun post(channel: TextChannel, message: String) = channel.sendMessage(message).complete()!!
}