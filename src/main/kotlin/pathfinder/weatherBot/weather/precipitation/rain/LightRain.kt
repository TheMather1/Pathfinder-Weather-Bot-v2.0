package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.snow.Snow

class LightRain(hour: Hour, hours: Long) : Rain(hour, hours) {
    override val fireRetardance = 10
    override fun description(prev: Precipitation?) = when (prev) {
        is LightRain -> null
        is Fog -> "Light rain showers down, washing away the fog."
        is Thunderstorm -> "The sound of thunder yields, and the rain fades to a light sprinkling."
        is Rain -> "The rain lightens, but doesn't stop."
        is Snow -> "As temperatures rise, the snowfall turns into a light shower."
        else -> "Rain lightly pours from the gray clouds lining the sky."
    }

    override val finished: String
        get() = "The sprinkling lets up."
}