package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.rain.Rain
import pathfinder.weatherBot.weather.precipitation.snow.Snow

class HeavyFog(weather: Weather, hours: Long) : Fog(weather, hours) {
    override val fireRetardance = 10
    override fun description(prev: Precipitation?) = when (prev) {
        is HeavyFog -> "The dense fog still clings to the air."
        is Fog -> "The fog thickens until it feels like soup."
        is Rain -> "As the rain stops, it gives way to a dense fog."
        is Snow -> "As the snow stops, it gives way to a dense fog."
        else -> "A thick fog falls upon the land."
    } + " (All vision beyond 5 ft. is obscured. Creatures more than 5 ft. away have concealment.)"

    override val finished: String
        get() = "The obscuring fog fades."
}