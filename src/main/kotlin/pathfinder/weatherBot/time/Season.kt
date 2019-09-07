package pathfinder.weatherBot.time

import pathfinder.weatherBot.location.Climate
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Frequency
import java.time.LocalDate

enum class Season {
    SPRING {
        override fun temp(location: Location): Long = location.climate.springTemp + location.elevation.adjustTemp
        override fun frequency(location: Location): Frequency = (if (location.climate == Climate.TROPICAL) Frequency.COMMON else Frequency.INTERMITTENT) + location.adjustPrecip
    },
    SUMMER {
        override fun temp(location: Location): Long = location.climate.summerTemp + location.elevation.adjustTemp
        override fun frequency(location: Location): Frequency = (if (location.climate == Climate.TROPICAL) Frequency.INTERMITTENT else Frequency.COMMON) + location.adjustPrecip
    },
    FALL {
        override fun temp(location: Location): Long = location.climate.fallTemp + location.elevation.adjustTemp
        override fun frequency(location: Location): Frequency = (if (location.climate == Climate.TROPICAL) Frequency.COMMON else Frequency.INTERMITTENT) + location.adjustPrecip
    },
    WINTER {
        override fun temp(location: Location): Long = location.climate.winterTemp + location.elevation.adjustTemp
        override fun frequency(location: Location): Frequency = Frequency.RARE + location.adjustPrecip
    };
    companion object {
        operator fun invoke(day: LocalDate): Season = when(day.dayOfYear){
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