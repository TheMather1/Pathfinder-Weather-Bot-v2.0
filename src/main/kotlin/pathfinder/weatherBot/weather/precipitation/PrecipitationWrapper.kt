package pathfinder.weatherBot.weather.precipitation

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import pathfinder.diceSyntax.d
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.time.Season
import pathfinder.weatherBot.weather.Described
import pathfinder.weatherBot.weather.Temperature
import pathfinder.weatherBot.weather.Wind
import java.io.Serializable
import java.time.LocalDateTime

@Embeddable
class PrecipitationWrapper(
    @Column(name = "PRECIP_START")
    val start: LocalDateTime,
    @Column(name = "PRECIP_END")
    val end: LocalDateTime,
    @Column(name = "PRECIP_TYPE")
    val type: Precipitation,
    @Column(name = "PRECIP_WIND")
    @Enumerated(EnumType.STRING)
    val windOverride: Wind? = null
) {

    fun coerceForTime(nextHour: Hour): PrecipitationWrapper? = if (nextHour.time < end) this else null

    override fun toString() = type.toString()

    companion object {
        private fun dry(season: Season, config: GuildConfig): Boolean = (1 d 100).toInt() > season.frequency(config).chance
        fun thunder(): Boolean = (1 d 100).toInt() <= 10
        fun blizzard(wind: Wind): Boolean = wind >= Wind.SEVERE && (1 d 100).toInt() <= 40
        fun blizzardDuration(start: LocalDateTime) = if ((1 d 100).toInt() <= 20) start.plusHours((2 d 12).toLong()) else null

        operator fun invoke(
            config: GuildConfig, start: LocalDateTime, season: Season, temp: Temperature
        ): PrecipitationWrapper = when {
            dry(season, config) -> PrecipitationWrapper(start, start.plusHours(1), None.NONE)
            temp.freezing -> config.intensity.frozen(start)
            else -> config.intensity.wet(start, temp)
        }
        fun heavySnow(start: LocalDateTime, end: LocalDateTime): PrecipitationWrapper {
            val wind = Wind()
            return when {
                thunder() -> PrecipitationWrapper.thundersnow(start, end)
                blizzard(wind) -> PrecipitationWrapper(start, blizzardDuration(start) ?: end, Snow.BLIZZARD, wind)
                else -> PrecipitationWrapper(start, end, Snow.HEAVY_SNOW, wind)
            }
        }
        fun thundersnow(start: LocalDateTime, end: LocalDateTime): PrecipitationWrapper = Thunder.wind.let {
            if (blizzard(it)) PrecipitationWrapper(start, end, Snow.THUNDER_BLIZZARD, it)
            else PrecipitationWrapper(start, end, Snow.THUNDERSNOW, it)
        }
    }
}

