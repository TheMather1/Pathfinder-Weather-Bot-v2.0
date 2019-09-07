package pathfinder.weatherBot.location

import java.lang.RuntimeException


class Location {
    lateinit var climate : Climate
    lateinit var elevation : Elevation
    var desert = false
    val adjustPrecip = climate.adjustPrecip + elevation.adjustPrecip
    val intensity = elevation.basePrecipitation + climate.adjustPrecip
    val frequency = climate.adjustPrecip
    var snowLevel: Double = 0.0

    fun isSet(): Boolean = ::climate.isInitialized && ::elevation.isInitialized
    fun missing(): String = when (::climate.isInitialized to ::elevation.isInitialized){
            true to true -> "climate and elevation"
            true to false -> "climate"
            false to true -> "elevation"
            else -> throw RuntimeException("Attempting to read missing environment factors when both are present.")
    }
}