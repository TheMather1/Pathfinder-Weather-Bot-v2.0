package pathfinder.weatherBot.weather.precipitation

import pathfinder.weatherBot.d
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.time.Season
import pathfinder.weatherBot.weather.Described
import java.io.Serializable
import kotlin.reflect.full.primaryConstructor

abstract class Precipitation(val hour: Hour, val hours: Long): Described<Precipitation>, Serializable {
    companion object {
        private fun dry(season: Season, config: GuildConfig): Boolean = (1 d 100) > season.frequency(config).chance
        private fun frozen(temp: Long): Boolean = temp <= 32
        fun thunder(): Boolean = (1 d 100) <= 10

        operator fun invoke(config: GuildConfig, hour: Hour): Precipitation? = when {
            dry(hour.day.season, config) -> null
            frozen(hour.temp) -> config.intensity.frozen(hour)
            else -> config.intensity.wet(hour)
        }
    }

    fun next(nextHour: Hour): Precipitation? = if (hours > 0) this::class.primaryConstructor?.call(nextHour, hours-1) else null

    abstract fun fall()

    abstract val fireRetardance: Int
    val tempAdjust = 0
}

