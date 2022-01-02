package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.snow.Snow

class Drizzle(hour: Hour, hours: Long) : Rain(hour, hours) {
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