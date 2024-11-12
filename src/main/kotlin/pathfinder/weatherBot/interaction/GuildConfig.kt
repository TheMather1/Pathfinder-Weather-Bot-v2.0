package pathfinder.weatherBot.interaction

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import pathfinder.weatherBot.location.Climate
import pathfinder.weatherBot.location.Elevation
import pathfinder.weatherBot.weather.precipitation.Intensity
import java.io.Serializable
import java.time.ZoneId

@Entity
class GuildConfig(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    var outputChannel: Long,
    var active: Boolean = false,
    var forecastRole: Long? = null,
    var climate: Climate = Climate.TEMPERATE,
    var elevation: Elevation = Elevation.SEA_LEVEL,
    var desert: Boolean = false,
    var timezone: ZoneId = ZoneId.systemDefault()
) {
    val frequencyMod: Int
        get() = climate.adjustPrecip + elevation.adjustPrecip
    val intensity: Intensity
        get() = elevation.basePrecipitation + climate.adjustPrecip

    fun override(config: GuildConfig) {
        outputChannel = config.outputChannel
        active = config.active
        forecastRole = config.forecastRole
        climate = config.climate
        elevation = config.elevation
        desert = config.desert
        timezone = config.timezone
    }
}
