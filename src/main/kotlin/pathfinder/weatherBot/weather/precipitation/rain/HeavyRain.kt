package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.snow.Snow

open class HeavyRain(weather: Weather, hours: Long) : Rain(weather, hours) {
    override val fireRetardance = 25
    override fun description(prev: Precipitation?) = when (prev) {
        is HeavyRain -> "The torrential rain keeps pouring down."
        is Fog -> "A heavy rain shower starts, washing away the fog."
        is Thunderstorm -> "The sound of thunder yields, but the rain remains."
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