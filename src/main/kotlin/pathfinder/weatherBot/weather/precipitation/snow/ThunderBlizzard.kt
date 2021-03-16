package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder

class ThunderBlizzard(weather: Weather, hours: Long, override val wind: Wind) : Blizzard(weather, hours),
        Thunder {
    override fun description(prev: Precipitation?) =
        "Our sight is obscured by the plumes of snow, and our hearing is plagued by roaring thunder. It's a thunder blizzard! "

    override val finished: String
        get() = "The Thunder Blizzard clears out! Thank the Gods!"
}