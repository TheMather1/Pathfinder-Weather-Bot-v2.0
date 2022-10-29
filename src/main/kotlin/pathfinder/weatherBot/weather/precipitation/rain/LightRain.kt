package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.precipitation.None
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.snow.Snow
import java.time.LocalDateTime

class LightRain(start: LocalDateTime, end: LocalDateTime) : Rain(start, end) {
    override val fireRetardance = 10
    override fun print(prev: Precipitation?) = when (prev) {
        is LightRain -> null
        is Fog -> "Light rain showers down, washing away the fog."
        is Thunderstorm -> "The sound of thunder yields, and the rain fades to a light sprinkling."
        is Rain -> "The rain lightens, but doesn't stop."
        is Snow -> "As temperatures rise, the snowfall turns into a light shower."
        is None -> "Rain lightly pours from the gray clouds lining the sky."
        else -> "There is a light rain."
    }

    override val finished: String
        get() = "The sprinkling lets up."
}
