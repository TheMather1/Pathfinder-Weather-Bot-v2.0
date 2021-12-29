package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.rain.Rain
import pathfinder.weatherBot.weather.precipitation.snow.Snow

class HeavyFog(weather: Weather, hours: Long) : Fog(weather, hours) {
    override val fireRetardance = 10
    override fun description(prev: Precipitation?) = when (prev) {
        is HeavyFog -> null
        is Fog -> "The fog thickens into a smothering brume."
        is Rain -> "The rain stops, allowing a heavy fog to form."
        is Snow -> "The snowfall ceases, letting a soupy-thick fog obscure our vision."
        else -> "A thick fog rolls in."
    }?.plus(" (All vision beyond 5 ft. is obscured. Creatures more than 5 ft. away have concealment.)")

    override val finished: String
        get() = "The obscuring fog dissipates."
}