package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder

class ThunderBlizzard(weather: Weather, hours: Long, override val wind: Wind) : Blizzard(weather, hours),
        Thunder {
    override fun description(prev: Precipitation?) = when(prev) {
        is ThunderBlizzard -> null
        is Blizzard -> "The blizzard holds strong as thunder starts to roar out in the distance."
        is Thundersnow -> "Intense winds turn the thundersnow into a thunder blizzard."
        is Snow -> "Intense winds turns the snow into a blizzard as thunder roars out in the distance."
        else -> "An intense blizzard rolls in as thunder roars out in the distance"
    }?.plus(" Be careful about walking outside in metal armor.")

    override val finished: String
        get() = "The thunder blizzard clears out."
}