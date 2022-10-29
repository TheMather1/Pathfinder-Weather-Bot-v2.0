package pathfinder.weatherBot.weather.precipitation

import pathfinder.weatherBot.d
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.time.Season
import pathfinder.weatherBot.weather.Described
import pathfinder.weatherBot.weather.Temperature
import java.io.Serializable
import java.time.LocalDateTime

abstract class Precipitation(val start: LocalDateTime, val end: LocalDateTime) : Described<Precipitation>,
    Serializable {
    companion object {
        private fun dry(season: Season, config: GuildConfig): Boolean = (1 d 100) > season.frequency(config).chance
        fun thunder(): Boolean = (1 d 100) <= 10

        operator fun invoke(
            config: GuildConfig, start: LocalDateTime, season: Season, temp: Temperature
        ): Precipitation = when {
            dry(season, config) -> None(start)
            temp.freezing -> config.intensity.frozen(start)
            else -> config.intensity.wet(start, temp)
        }
    }

    fun coerceForTime(nextHour: Hour): Precipitation? = if (nextHour.time < end) this else null

    abstract fun fall()

    abstract val fireRetardance: Int
    val tempAdjust = 0

    override fun toString() = "([A-Z])".toRegex().replace(this::class.simpleName!!, " $1").trim()
}

