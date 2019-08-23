package weatherBot.time

import weatherBot.location.Climate
import weatherBot.location.Location
import weatherBot.precipitation.Frequency
import java.time.LocalDate

enum class Season {
    SPRING {
        override fun temp(): Long = Location.climate.springTemp + Location.elevation.adjustTemp
        override fun frequency(): Frequency = (if (Location.climate == Climate.TROPICAL) Frequency.COMMON else Frequency.INTERMITTENT) + Location.adjustPrecip
    },
    SUMMER {
        override fun temp(): Long = Location.climate.summerTemp + Location.elevation.adjustTemp
        override fun frequency(): Frequency = (if (Location.climate == Climate.TROPICAL) Frequency.INTERMITTENT else Frequency.COMMON) + Location.adjustPrecip
    },
    FALL {
        override fun temp(): Long = Location.climate.fallTemp + Location.elevation.adjustTemp
        override fun frequency(): Frequency = (if (Location.climate == Climate.TROPICAL) Frequency.COMMON else Frequency.INTERMITTENT) + Location.adjustPrecip
    },
    WINTER {
        override fun temp(): Long = Location.climate.springTemp + Location.elevation.adjustTemp
        override fun frequency(): Frequency = Frequency.RARE + Location.adjustPrecip
    };
    companion object {
        fun generate(day: LocalDate): Season = when(day.dayOfYear){
            in 0..45 -> WINTER
            in 45..137 -> SPRING
            in 138..228 -> SUMMER
            in 229..320 -> FALL
            else -> WINTER
        }
    }

    abstract fun temp(): Long
    abstract fun frequency(): Frequency
}