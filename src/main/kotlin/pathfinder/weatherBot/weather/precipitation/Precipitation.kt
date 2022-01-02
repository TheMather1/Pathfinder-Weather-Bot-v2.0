package pathfinder.weatherBot.weather.precipitation

import pathfinder.weatherBot.d
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Described
import java.io.Serializable
import kotlin.reflect.full.primaryConstructor

abstract class Precipitation(val hour: Hour, val hours: Long): Described<Precipitation>, Serializable {
    companion object {
        private fun dry(hour: Hour): Boolean = (1 d 100) > hour.day.season.frequency(hour.day.forecast.biome).chance
        private fun frozen(temp: Long): Boolean = temp <= 32
        fun thunder(): Boolean = (1 d 100) <= 10

        operator fun invoke(hour: Hour): Precipitation? = when {
            dry(hour) -> null
            frozen(hour.temp) -> hour.day.forecast.biome.intensity.frozen(hour)
            else -> hour.day.forecast.biome.intensity.wet(hour)
        }
    }

    fun next(nextHour: Hour): Precipitation? = if (hours > 0) this::class.primaryConstructor?.call(nextHour, hours-1) else null

    abstract fun fall()

    abstract val fireRetardance: Int
    val tempAdjust = 0
}

