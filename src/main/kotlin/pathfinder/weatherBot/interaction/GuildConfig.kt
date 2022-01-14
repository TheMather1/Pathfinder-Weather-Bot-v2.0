package pathfinder.weatherBot.interaction

import pathfinder.weatherBot.location.Climate
import pathfinder.weatherBot.location.Elevation
import pathfinder.weatherBot.weather.precipitation.Intensity
import java.io.Serializable

data class GuildConfig(
    var outputChannel: Long,
    var active: Boolean = false,
    var forecastRole: Long? = null,
    var climate: Climate = Climate.TEMPERATE,
    var elevation: Elevation = Elevation.SEA_LEVEL,
    var desert: Boolean = false
): Serializable {
    val frequencyMod: Int
        get() = climate.adjustPrecip + elevation.adjustPrecip
    val intensity: Intensity
        get() = elevation.basePrecipitation + climate.adjustPrecip
}