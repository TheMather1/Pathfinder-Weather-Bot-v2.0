package weatherBot.time

import weatherBot.location.Climate
import weatherBot.location.Location
import weatherBot.precipitation.Frequency

enum class Season() {
    SPRING(){
        override fun temp(location: Location): Int = location.climate.springTemp + location.tempVariation() + location.elevation.adjustTemp
        override fun frequency(location: Location): Frequency = (if (location.climate == Climate.TROPICAL) Frequency.COMMON else Frequency.INTERMITTENT) + location.adjustPrecip
    },
    SUMMER(){
        override fun temp(location: Location): Int = location.climate.summerTemp + location.elevation.adjustTemp
        override fun frequency(location: Location): Frequency = (if (location.climate == Climate.TROPICAL) Frequency.INTERMITTENT else Frequency.COMMON) + location.adjustPrecip
    },
    FALL(){
        override fun temp(location: Location): Int = location.climate.fallTemp + location.elevation.adjustTemp
        override fun frequency(location: Location): Frequency = (if (location.climate == Climate.TROPICAL) Frequency.COMMON else Frequency.INTERMITTENT) + location.adjustPrecip
    },
    WINTER(){
        override fun temp(location: Location): Int = location.climate.springTemp + location.elevation.adjustTemp
        override fun frequency(location: Location): Frequency = Frequency.RARE + location.adjustPrecip
    };

    abstract fun temp(location: Location): Int
    abstract fun frequency(location: Location): Frequency
}