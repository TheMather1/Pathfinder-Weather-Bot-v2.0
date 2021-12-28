package pathfinder.weatherBot.interaction

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Role
import net.dv8tion.jda.api.entities.TextChannel
import pathfinder.weatherBot.location.Biome
import pathfinder.weatherBot.time.Clock
import pathfinder.weatherBot.time.Forecast
import java.io.Serializable

data class Client(internal val serverId: String, private var channelId: String, private val jda: JDA) : Serializable {
//    internal val savestate = Date
    internal val clock = Clock(this)
    internal val biome = Biome()
    internal val forecast = Forecast(biome)
    internal var outputChannel: TextChannel
        get() = jda.getTextChannelById(channelId)!!
        set(channel) {
            channelId = channel.id
        }
    val commandHandler = CommandHandler(this)
    var prefix = "w!"
    var forecastRole: Long? = null
}