package pathfinder.weatherBot.time

import pathfinder.weatherBot.location.Climate.TROPICAL
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Frequency
import pathfinder.weatherBot.weather.precipitation.Frequency.*
import java.time.LocalDate

enum class Season {
    SPRING {
        override fun temp(location: Location) = location.climate.springTemp + location.elevation.adjustTemp
        override fun frequency(location: Location) = (if (location.climate == TROPICAL) COMMON else INTERMITTENT) + location.frequency
    },
    SUMMER {
        override fun temp(location: Location) = location.climate.summerTemp + location.elevation.adjustTemp
        override fun frequency(location: Location) = (if (location.climate == TROPICAL) INTERMITTENT else COMMON) + location.frequency
    },
    FALL {
        override fun temp(location: Location) = location.climate.fallTemp + location.elevation.adjustTemp
        override fun frequency(location: Location) = (if (location.climate == TROPICAL) COMMON else INTERMITTENT) + location.frequency
    },
    WINTER {
        override fun temp(location: Location) = location.climate.winterTemp + location.elevation.adjustTemp
        override fun frequency(location: Location) = RARE + location.frequency
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

    abstract fun temp(location: Location): Long
    abstract fun frequency(location: Location): Frequency
}