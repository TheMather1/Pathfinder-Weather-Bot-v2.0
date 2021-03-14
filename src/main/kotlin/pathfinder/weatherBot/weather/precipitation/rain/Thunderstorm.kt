package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder

class Thunderstorm(weather: Weather, hours: Long) : HeavyRain(weather , hours),
        Thunder {
    override fun description(prev: Precipitation?): String {
        "As blackened clouds form overhead, a booming thunderstorm begins! Maybe take off the metal helmet,just in case?"
    }

    override val finished: String
        get() = "The sounds of thunder cease, and soon after, the torrent does as well."

    override fun fall() {
        TODO("not implemented")
    }
}