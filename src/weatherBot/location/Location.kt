package weatherBot.location

import weatherBot.time.Season

class Location {
    val climate = Climate.valueOf("placeholder")
    val elevation = Elevation.valueOf("placeholder")
    val adjustPrecip = climate.adjustPrecip + elevation.adjustPrecip
    val desert = Boolean
    val intensity = elevation.basePrecipitation + climate.adjustPrecip
    val frequency = climate.adjustPrecip

    public fun tempVariation(season: Season): Int{
        season.temp(this)
    }
}