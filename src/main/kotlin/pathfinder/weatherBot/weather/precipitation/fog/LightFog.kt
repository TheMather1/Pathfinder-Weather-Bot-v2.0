package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

class LightFog(weather: Weather, hours: Long) : Fog(weather, hours) {

    override val fireRetardance = 0

    override fun description(prev: Precipitation?): String {
        "A light fog rolls in, (sight range is reduced to 3/4s what it usually is, and you suffer a -2 to perception and ranged attacks)"
    }

    override val finished: String
        get() = "The fog dissipates, we can see clearly now!"
}