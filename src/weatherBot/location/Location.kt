package weatherBot.location

import weatherBot.time.Season
import kotlin.math.absoluteValue

object Location {
    val climate = Climate.valueOf("placeholder")
    val elevation = Elevation.valueOf("placeholder")
    val adjustPrecip = climate.adjustPrecip + elevation.adjustPrecip
    val desert = Boolean
    val intensity = elevation.basePrecipitation + climate.adjustPrecip
    val frequency = climate.adjustPrecip
}