package pathfinder.weatherBot.location

import pathfinder.weatherBot.weather.precipitation.Intensity
import pathfinder.weatherBot.weather.precipitation.Intensity.HEAVY
import pathfinder.weatherBot.weather.precipitation.Intensity.MEDIUM

enum class Elevation(val adjustTemp: Long, val adjustPrecip: Int, val basePrecipitation: Intensity) {
    SEA_LEVEL(
        10,
        0,
        HEAVY
    ),
    LOWLAND(
        0,
        0,
        MEDIUM
    ),
    HIGHLAND(
        -10,
        -1,
        MEDIUM
    ),
    HIGHLAND_ARID(
        +10,
        -1,
        MEDIUM
    ),
    HIGHLAND_MOUNTAIN(
        -20,
        -1,
        MEDIUM
    )
}