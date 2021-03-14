package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

class LightRain(weather: Weather, hours: Long) : Rain(weather, hours) {
    override val fireRetardance = 10
    override fun description(prev: Precipitation?): String {
        TODO("not implemented")
    }

    override val finished: String
        get() = TODO("not implemented")
}