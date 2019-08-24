package weatherBot.location

object Location {
    val climate = Climate.valueOf("placeholder")
    val elevation = Elevation.valueOf("placeholder")
    val adjustPrecip = climate.adjustPrecip + elevation.adjustPrecip
    val desert = Boolean
    val intensity = elevation.basePrecipitation + climate.adjustPrecip
    val frequency = climate.adjustPrecip
    var snowLevel: Double = 0.0
}