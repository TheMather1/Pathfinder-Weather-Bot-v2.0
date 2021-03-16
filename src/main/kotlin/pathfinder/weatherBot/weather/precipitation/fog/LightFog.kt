package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.rain.Rain
import pathfinder.weatherBot.weather.precipitation.snow.Snow

class LightFog(weather: Weather, hours: Long) : Fog(weather, hours) {

    override val fireRetardance = 0

    override fun description(prev: Precipitation?) = when (prev) {
        is LightFog -> "The thin fog still clings to the air."
        is Fog -> "The fog fades to a thin veil."
        is Rain -> "As the rain stops, it gives way to a light fog."
        is Snow -> "As the snow stops, it gives way to a light fog."
        else -> "A light fog rolls in."
    } + " (Sight is reduced to 3/4 range, and you suffer a -2 to perception and ranged attacks.)"

    override val finished: String
        get() = "The fog dissipates, we can see clearly now!"
}