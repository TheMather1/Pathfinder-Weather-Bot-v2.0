package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

class MediumRain(weather: Weather, hours: Long) : Rain(weather, hours) {
    override val fireRetardance = 15
    override fun description(prev: Precipitation?): String {
        "A healthy rainfall occurs overhead."
    }

    override val finished: String
        get() = "The rainfall ceases."
}