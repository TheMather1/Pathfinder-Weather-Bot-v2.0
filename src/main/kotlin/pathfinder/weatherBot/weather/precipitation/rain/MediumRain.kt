package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.snow.Snow

class MediumRain(weather: Weather, hours: Long) : Rain(weather, hours) {
    override val fireRetardance = 15
    override fun description(prev: Precipitation?) = when (prev) {
        is Fog -> "Rain pours down, washing away the fog."
        is Thunderstorm -> "The sound of thunder yields, and the rain calms somewhat."
        is LightRain -> "The rain intensifies somewhat."
        is MediumRain -> "The rain continues to fall."
        is HeavyRain -> "The rain calms somewhat."
        is Snow -> "As temperatures rise, the snow gives way to rain."
        else -> "A healthy rainfall pours overhead."
    }

    override val finished: String
        get() = "The rainfall ceases."
}