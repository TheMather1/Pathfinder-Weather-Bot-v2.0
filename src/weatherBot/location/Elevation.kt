package weatherBot.location

import weatherBot.precipitation.Intensity
import weatherBot.precipitation.Precipitation

enum class Elevation(val adjustTemp: Int, val adjustPrecip: Int, val basePrecipitation: Intensity) {
    SEA_LEVEL(
        10,
        0,
        Intensity.HEAVY
    ),
    LOWLAND(
        0,
        0,
        Intensity.MEDIUM
    ),
    HIGHLAND(
        -10,
        -1,
        Intensity.MEDIUM
    ),
    HIGHLAND_ARID(
        +10,
        -1,
        Intensity.MEDIUM
    ),
    HIGHLAND_MOUNTAIN(
        -20,
        -1,
        Intensity.MEDIUM
    )
}