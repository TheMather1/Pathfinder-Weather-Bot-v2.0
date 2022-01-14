package pathfinder.weatherBot.time

import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.location.Climate.TROPICAL
import pathfinder.weatherBot.weather.precipitation.Frequency
import pathfinder.weatherBot.weather.precipitation.Frequency.*
import java.time.LocalDate

enum class Season {
    SPRING {
        override fun temp(config: GuildConfig) = config.climate.springTemp + config.elevation.adjustTemp
        override fun frequency(config: GuildConfig) = if (config.desert) DROUGHT
        else (if (config.climate == TROPICAL) COMMON else INTERMITTENT) + config.frequencyMod
    },
    SUMMER {
        override fun temp(config: GuildConfig) = config.climate.summerTemp + config.elevation.adjustTemp
        override fun frequency(config: GuildConfig) = if (config.desert) DROUGHT
        else (if (config.climate == TROPICAL) INTERMITTENT else COMMON) + config.frequencyMod
    },
    FALL {
        override fun temp(config: GuildConfig) = config.climate.fallTemp + config.elevation.adjustTemp
        override fun frequency(config: GuildConfig) = if (config.desert) DROUGHT
        else (if (config.climate == TROPICAL) COMMON else INTERMITTENT) + config.frequencyMod
    },
    WINTER {
        override fun temp(config: GuildConfig) = config.climate.winterTemp + config.elevation.adjustTemp
        override fun frequency(config: GuildConfig) = if (config.desert) DROUGHT
        else RARE + config.frequencyMod
    };

    companion object {
        operator fun invoke(day: LocalDate) = when (day.dayOfYear) {
            in 0..45 -> WINTER
            in 45..137 -> SPRING
            in 138..228 -> SUMMER
            in 229..320 -> FALL
            else -> WINTER
        }
    }

    abstract fun temp(config: GuildConfig): Long
    abstract fun frequency(config: GuildConfig): Frequency
}