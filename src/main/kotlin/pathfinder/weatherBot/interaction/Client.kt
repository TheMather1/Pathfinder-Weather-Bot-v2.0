package pathfinder.weatherBot.interaction

import net.dv8tion.jda.api.entities.TextChannel
import pathfinder.weatherBot.Bot
import pathfinder.weatherBot.location.Biome
import pathfinder.weatherBot.time.Clock
import pathfinder.weatherBot.time.Forecast
import java.io.Serializable

data class Client(internal val serverId: String, private var channelId: String) : Serializable {
    internal val clock = Clock(this)
    internal val biome = Biome()
    internal val forecast = Forecast(biome)
    internal var outputChannel: TextChannel
        get() = Bot.botUser.getTextChannelById(channelId)!!
        set(channel) {
            channelId = channel.id
        }
    val commandHandler = CommandHandler(this)
    var prefix = "w!"
}