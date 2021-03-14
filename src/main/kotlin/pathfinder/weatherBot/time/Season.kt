package pathfinder.weatherBot.time

import pathfinder.weatherBot.location.Biome
import pathfinder.weatherBot.location.Climate.TROPICAL
import pathfinder.weatherBot.weather.precipitation.Frequency
import pathfinder.weatherBot.weather.precipitation.Frequency.*
import java.time.LocalDate

enum class Season {
    SPRING {
        override fun temp(biome: Biome) = biome.climate.springTemp + biome.elevation.adjustTemp
        override fun frequency(biome: Biome) = if (biome.desert) DROUGHT
        else (if (biome.climate == TROPICAL) COMMON else INTERMITTENT) + biome.frequencyMod
    },
    SUMMER {
        override fun temp(biome: Biome) = biome.climate.summerTemp + biome.elevation.adjustTemp
        override fun frequency(biome: Biome) = if (biome.desert) DROUGHT
        else (if (biome.climate == TROPICAL) INTERMITTENT else COMMON) + biome.frequencyMod
    },
    FALL {
        override fun temp(biome: Biome) = biome.climate.fallTemp + biome.elevation.adjustTemp
        override fun frequency(biome: Biome) = if (biome.desert) DROUGHT
        else (if (biome.climate == TROPICAL) COMMON else INTERMITTENT) + biome.frequencyMod
    },
    WINTER {
        override fun temp(biome: Biome) = biome.climate.winterTemp + biome.elevation.adjustTemp
        override fun frequency(biome: Biome) = if (biome.desert) DROUGHT
        else RARE + biome.frequencyMod
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

    abstract fun temp(biome: Biome): Long
    abstract fun frequency(biome: Biome): Frequency
}