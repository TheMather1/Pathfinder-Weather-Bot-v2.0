package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

class MediumFog(weather: Weather, hours: Long) : Fog(weather, hours) {
    override val fireRetardance = 5
    override fun description(prev: Precipitation?): String {
        "A moderately thick fog rolls in (vision range is reduced to half what it usually is. Take a -4 to perception and ranged attack rolls)"
    }

    override val finished: String
        get() = "The fog fades away, allowing us to see once more!"
}