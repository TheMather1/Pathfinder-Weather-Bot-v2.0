package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.snow.Snow

class Drizzle(weather: Weather, hours: Long) : Rain(weather, hours) {
    override val fireRetardance = 5
    override fun description(prev: Precipitation?) = when (prev) {
        is Drizzle -> "The drizzle continues."
        is Fog -> "A refreshing drizzle washes away the fog."
        is Rain -> "The rain slows to a light drizzle."
        is Snow -> "As temperatures rise, the snow yields to a light drizzle."
        else -> "A refreshing drizzle drips across the land. Giving the plant life a nice drink."
    }

    override val finished: String
        get() = "The drizzle ceases."
}