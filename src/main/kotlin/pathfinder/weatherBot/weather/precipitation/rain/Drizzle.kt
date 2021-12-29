package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.snow.Snow

class Drizzle(weather: Weather, hours: Long) : Rain(weather, hours) {
    override val fireRetardance = 5
    override fun description(prev: Precipitation?) = when (prev) {
        is Drizzle -> null
        is Fog -> "The fog is washed away by a drizzle overhead."
        is Rain -> "The rain slows to a light drizzle."
        is Snow -> "As temperatures rise, the falling snowflakes turn into water droplets."
        else -> "A refreshing drizzle covers the area with a fine coat of droplets."
    }

    override val finished: String
        get() = "The drizzle ends."
}