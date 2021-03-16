package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.snow.Snow

class LightRain(weather: Weather, hours: Long) : Rain(weather, hours) {
    override val fireRetardance = 10
    override fun description(prev: Precipitation?) = when (prev) {
        is LightRain -> "The mild downpour continues."
        is Fog -> "Light rain pours down, washing away the fog."
        is Thunderstorm -> "The sound of thunder yields, and the rain fades to a mild downpour."
        is Rain -> "The rain fades to a mild downpour."
        is Snow -> "As temperatures rise, the snow gives way to a mild downpour."
        else -> "Rain lightly pours from the gray clouds lining the sky."
    }

    override val finished: String
        get() = "The sprinkling lets up."
}