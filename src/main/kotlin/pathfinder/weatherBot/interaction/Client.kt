package pathfinder.weatherBot.interaction

import net.dv8tion.jda.api.entities.Guild
import pathfinder.weatherBot.location.Biome
import pathfinder.weatherBot.time.Clock
import pathfinder.weatherBot.time.Forecast
import java.io.Serializable

class Client(
    guild: Guild
) : Serializable {
    internal var channelId = guild.defaultChannel!!.idLong
    //    internal val savestate = Date
    internal val clock = Clock(this)
    internal val biome = Biome()
    internal val forecast = Forecast(biome)
    @Transient
    val commandHandler = CommandHandler(this)
    var prefix = "w!"
    var forecastRole: Long? = null
}