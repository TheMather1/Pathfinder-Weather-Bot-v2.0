package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

class MediumFog(weather: Weather, hours: Long) : Fog(weather, hours) {
    override val fireRetardance = 5
    override fun description(prev: Precipitation?): String {
        TODO("not implemented")
    }

    override val finished: String
        get() = TODO("not implemented")
}