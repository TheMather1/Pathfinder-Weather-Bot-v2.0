package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

class LightRain(weather: Weather, hours: Long) : Rain(weather, hours) {
    override val fireRetardance = 10
    override fun description(prev: Precipitation?): String {
        "Rain lightly pours from the gray clouds lining the sky."
    }

    override val finished: String
        get() = "The sprinkling lets up."
}