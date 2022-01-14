package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.snow.Snow
import java.time.LocalDateTime

class MediumRain(start: LocalDateTime, end: LocalDateTime) : Rain(start, end) {
    override val fireRetardance = 15
    override fun description(prev: Precipitation?) = when (prev) {
        is MediumRain -> null
        is Fog -> "Rain pours down, washing away the fog."
        is Thunderstorm -> "The sound of thunder yields, and the rain calms somewhat."
        is LightRain -> "The begins falling somewhat harder."
        is HeavyRain -> "The rain begins to calm down a bit."
        is Snow -> "As temperatures rise, the snow gives way to rain."
        else -> "A healthy rainfall pours overhead."
    }

    override val finished: String
        get() = "The rainfall ceases."
}