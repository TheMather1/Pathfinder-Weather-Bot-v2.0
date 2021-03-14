package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

open class HeavyRain(weather: Weather, hours: Long) : Rain(weather, hours) {
    override val fireRetardance = 25
    override fun description(prev: Precipitation?): String {
        "Dark, heavy clouds begin pouring rain across the area. Don't wear your fancy clothes out, they'll probably get ruined!"
    }

    override val finished: String
        get() = "The clouds have been drained, and the downpour ends."

    override fun fall() {
        TODO("not implemented")
    }
}