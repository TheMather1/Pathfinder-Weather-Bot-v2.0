package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.precipitation.None
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.snow.Snow
import java.time.LocalDateTime

class Drizzle(start: LocalDateTime, end: LocalDateTime) : Rain(start, end) {
    override val fireRetardance = 5
    override fun print(prev: Precipitation?) = when (prev) {
        is Drizzle -> null
        is Fog -> "The fog is washed away by a drizzle overhead."
        is Rain -> "The rain slows to a light drizzle."
        is Snow -> "As temperatures rise, the falling snowflakes turn into water droplets."
        is None -> "A refreshing drizzle covers the area with a fine coat of droplets."
        else -> "There is a light drizzle."
    }

    override val finished: String
        get() = "The drizzle ends."
}
