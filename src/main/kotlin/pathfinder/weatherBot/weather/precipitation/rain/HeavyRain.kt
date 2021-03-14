package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

open class HeavyRain(weather: Weather, hours: Long) : Rain(weather, hours) {
    override val fireRetardance = 25
    override fun description(prev: Precipitation?): String {
        TODO("not implemented")
    }

    override val finished: String
        get() = TODO("not implemented")

    override fun fall() {
        TODO("not implemented")
    }
}