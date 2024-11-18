package pathfinder.weatherBot.interaction

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import pathfinder.weatherBot.location.Climate
import pathfinder.weatherBot.location.Elevation
import pathfinder.weatherBot.weather.precipitation.Intensity
import java.time.ZoneId

@Suppress("JpaObjectClassSignatureInspection")
@Entity
class GuildConfig(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    var outputChannel: Long,
    var alertsChannel: Long? = null,
    var active: Boolean = false,
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
        alertsChannel = config.alertsChannel
        climate = config.climate
        elevation = config.elevation
        desert = config.desert
        timezone = config.timezone
    }
}
