package pathfinder.weatherBot.location

import pathfinder.weatherBot.config

object Location {
    val climate = Climate.valueOf(config["climate"].toString())
    val elevation = Elevation.valueOf(config["elevation"].toString())
    val adjustPrecip = climate.adjustPrecip + elevation.adjustPrecip
    val desert: Boolean = config["desert"].toString().toBoolean()
    val intensity = elevation.basePrecipitation + climate.adjustPrecip
    val frequency = climate.adjustPrecip
    var snowLevel: Double = 0.0
}