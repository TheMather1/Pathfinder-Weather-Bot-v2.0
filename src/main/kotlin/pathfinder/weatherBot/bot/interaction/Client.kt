package pathfinder.weatherBot.bot.interaction

import pathfinder.weatherBot.location.Biome
import pathfinder.weatherBot.time.Forecast
import java.io.Serializable

data class Client(internal val serverId : String, internal val channelId : String): Serializable{
    internal val biome = Biome()
    internal val forecast = Forecast(biome)
    val commandHandler = CommandHandler(this)
    val prefix = "~"
}