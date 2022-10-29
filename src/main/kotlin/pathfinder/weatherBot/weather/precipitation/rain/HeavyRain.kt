package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.precipitation.None
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.snow.Snow
import java.time.LocalDateTime

open class HeavyRain(start: LocalDateTime, end: LocalDateTime) : Rain(start, end) {
    override val fireRetardance = 25
    override fun print(prev: Precipitation?) = when (prev) {
        is Thunderstorm -> "The sound of thunder yields, but the rain remains."
        is HeavyRain -> null
        is Fog -> "A heavy rain shower starts, washing away the fog."
        is Rain -> "The rain begins beating down heavier and heavier.."
        is Snow -> "As temperatures rise, the snow gives way to a lashing rain."
        is None -> "Dark, heavy clouds begin pouring rain across the area."
        else -> "It is raining heavily."
    }

    override val finished: String
        get() = "The clouds have been drained, and the downpour ends."

    override fun fall() {
        TODO("not implemented")
    }
}
