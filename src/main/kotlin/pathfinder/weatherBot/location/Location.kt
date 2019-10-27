package pathfinder.weatherBot.location

import pathfinder.weatherBot.weather.precipitation.Intensity
import java.lang.RuntimeException


@ExperimentalUnsignedTypes
class Location {
    lateinit var climate: Climate
    lateinit var elevation: Elevation
    var desert = false
        set(value) {
            field = value
            groundMoisture = if(value) 0u else 100u
        }
    val frequency: Int by lazy { climate.adjustPrecip + elevation.adjustPrecip }
    val intensity: Intensity by lazy { elevation.basePrecipitation + climate.adjustPrecip }
    var snowLevel: Double = 0.0
    var groundMoisture: UByte = 100u

    fun isSet() = ::climate.isInitialized && ::elevation.isInitialized
    fun missing() = when (!::climate.isInitialized to !::elevation.isInitialized) {
        true to true -> "climate and elevation"
        true to false -> "climate"
        false to true -> "elevation"
        else -> throw RuntimeException("Attempting to read missing environment factors when both are present.")
    }
}