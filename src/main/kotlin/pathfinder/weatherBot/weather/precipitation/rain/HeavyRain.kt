package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.snow.Snow

open class HeavyRain(hour: Hour, hours: Long) : Rain(hour, hours) {
    override val fireRetardance = 25
    override fun description(prev: Precipitation?) = when (prev) {
        is Thunderstorm -> "The sound of thunder yields, but the rain remains."
        is HeavyRain -> null
        is Fog -> "A heavy rain shower starts, washing away the fog."
        is Rain -> "The rain begins beating down heavier and heavier.."
        is Snow -> "As temperatures rise, the snow gives way to a lashing rain."
        else -> "Dark, heavy clouds begin pouring rain across the area."
    }

    override val finished: String
        get() = "The clouds have been drained, and the downpour ends."

    override fun fall() {
        TODO("not implemented")
    }
}