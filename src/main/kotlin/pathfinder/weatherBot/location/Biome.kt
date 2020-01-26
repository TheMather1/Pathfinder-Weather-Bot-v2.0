package pathfinder.weatherBot.location

import pathfinder.weatherBot.location.Climate.TEMPERATE
import pathfinder.weatherBot.location.Elevation.SEA_LEVEL
import pathfinder.weatherBot.weather.precipitation.Intensity
import java.io.Serializable

@UseExperimental(ExperimentalUnsignedTypes::class)
data class Biome(var elevation: Elevation = SEA_LEVEL, var climate: Climate = TEMPERATE, var humidity: UByte = 100u) : Serializable {
    val frequencyMod: Int
        get() = climate.adjustPrecip + elevation.adjustPrecip
    val intensity: Intensity
        get() = elevation.basePrecipitation + climate.adjustPrecip
    var desert = false
    var snowLevel = 0.0
}