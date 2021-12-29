package pathfinder.weatherBot.weather.precipitation

import pathfinder.weatherBot.d
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Described
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.controller.frozen.Frozen
import pathfinder.weatherBot.weather.precipitation.controller.wet.Wet
import java.io.Serializable
import kotlin.reflect.full.primaryConstructor

abstract class Precipitation(val weather: Weather, val hours: Long): Described<Precipitation>, Serializable {
    companion object {
        private fun dry(hour: Hour): Boolean = (1 d 100) > hour.day.season.frequency(hour.day.forecast.biome).chance
        private fun frozen(temp: Long): Boolean = temp <= 32
        fun thunder(): Boolean = (1 d 100) <= 10

        operator fun invoke(weather: Weather): Precipitation? = when {
            dry(weather.hour) -> null
            frozen(weather.hour.temp) -> Frozen(weather)
            else -> Wet(weather)
        }
    }

    fun next(nextWeather: Weather): Precipitation? = if (hours > 0) this::class.primaryConstructor?.call(nextWeather, hours-1) else null

    abstract fun fall()

    abstract val fireRetardance: Int
    val tempAdjust = 0
}

