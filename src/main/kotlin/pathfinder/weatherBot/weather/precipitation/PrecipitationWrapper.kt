package pathfinder.weatherBot.weather.precipitation

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import pathfinder.diceSyntax.d
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.time.Season
import pathfinder.weatherBot.weather.Temperature
import pathfinder.weatherBot.weather.Wind
import java.time.LocalDateTime

@Suppress("JpaObjectClassSignatureInspection")
@Embeddable
class PrecipitationWrapper(
    @Column(name = "PRECIP_START")
    val start: LocalDateTime,
    @Column(name = "PRECIP_END")
    val end: LocalDateTime,
    @Column(name = "PRECIP_TYPE")
    @Suppress("JpaAttributeTypeInspection") //Manually defined JPAConverter
    val type: Precipitation,
    @Column(name = "PRECIP_WIND")
    @Enumerated(EnumType.STRING)
    val windOverride: Wind? = null
) {

    override fun toString() = type.toString()
        .replace('_', ' ').lowercase().replaceFirstChar(Char::uppercase)

    companion object {
        private fun dry(season: Season, config: GuildConfig): Boolean = (1 d 100).toInt() > season.frequency(config).chance

        operator fun invoke(
            config: GuildConfig, start: LocalDateTime, season: Season, temp: Temperature
        ): PrecipitationWrapper = when {
            dry(season, config) -> PrecipitationWrapper(start, start.plusHours(1), None.NONE)
            temp.freezing -> config.intensity.frozen(start)
            else -> config.intensity.wet(start, temp)
        }
    }
}

