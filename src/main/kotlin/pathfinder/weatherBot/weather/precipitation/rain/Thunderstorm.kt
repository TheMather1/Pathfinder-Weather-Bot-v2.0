package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.snow.Snow

class Thunderstorm(weather: Weather, hours: Long) : HeavyRain(weather, hours),
    Thunder {
    override fun description(prev: Precipitation?) = when (prev) {
        is Thunderstorm -> "Static still hangs in the air as the thunderstorm continues."
        is Fog -> "With a booming crack, a thunderstorm washes away the fog."
        is HeavyRain -> "Booming thunder can be heard as the "
        is Rain -> "As the rain stops, it gives way to a dense fog."
        is Snow -> "As the snow stops, it gives way to a dense fog."
        else -> "As blackened clouds form overhead, a booming thunderstorm begins."
    } + " Be careful about walking outside in metal armor."

    override val finished: String
        get() = "The sounds of thunder cease, and soon after, the torrent does as well."

    override fun fall() {
        TODO("not implemented")
    }
}