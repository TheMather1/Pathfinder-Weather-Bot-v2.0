package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

class Drizzle(weather: Weather, hours: Long) : Rain(weather, hours) {
    override val fireRetardance = 5
    override fun description(prev: Precipitation?): String {
        "A refreshing drizzle drips across the land. Giving the plant life a nice drink."
    }

    override val finished: String
        get() = "The drizzle ceases."
}