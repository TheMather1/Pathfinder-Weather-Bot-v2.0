package pathfinder.weatherBot.weather

import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import net.dv8tion.jda.api.entities.MessageEmbed
import pathfinder.diceSyntax.d
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.time.Season
import pathfinder.weatherBot.weather.precipitation.Fog
import pathfinder.weatherBot.weather.precipitation.None
import pathfinder.weatherBot.weather.precipitation.PrecipitationWrapper
import pathfinder.weatherBot.weather.precipitation.Snow.HEAVY_SNOW
import pathfinder.weatherBot.weather.precipitation.Thunder
import java.time.LocalDateTime

@Embeddable
class Weather(
    config: GuildConfig,
    season: Season, time:
    LocalDateTime,
    temp: Temperature,
    prevWeather: Weather?
) {
    val precipitation: PrecipitationWrapper =
        prevWeather?.precipitation?.takeIf { time < it.end } ?: PrecipitationWrapper(config, time, season, temp)

    private var cloudDuration = 0L
    @Enumerated(EnumType.STRING)
    val clouds: Clouds = when {
        precipitation.type !is None -> Clouds.OVERCAST
        prevWeather != null && prevWeather.cloudDuration > 0 -> {
            cloudDuration = prevWeather.cloudDuration - 1
            prevWeather.clouds.also { temp.temp += it.adjustTemp(season) }
        }
        else -> {
            cloudDuration = 2 + (1 d 3).toLong()
            Clouds().also { temp.temp += it.adjustTemp(season) }
        }
    }

    var windDuration = 0L
    @Enumerated(EnumType.STRING)
    val wind: Wind = when {
        precipitation.type is Fog -> Wind.LIGHT
        precipitation.type == HEAVY_SNOW -> precipitation.windOverride
        precipitation.type.isThunder -> if (prevWeather?.keepWind(precipitation) == true) {
            windDuration = prevWeather.windDuration - 1
            prevWeather.wind.let { Thunder.hurricane(it, temp) }
        } else {
            windDuration = 2 + (1 d 3).toLong()
            Thunder.wind.let { Thunder.hurricane(it, temp) }
        }
        else -> if (prevWeather?.keepWind(precipitation) == true) {
            windDuration = prevWeather.windDuration - 1
            prevWeather.wind
        } else {
            windDuration = 3 + (1 d 3).toLong()
            Wind()
        }
    }!!

    val warn: List<String>
        get() = listOfNotNull(precipitation.type.warn, wind.warn)

    fun iconUrl(time: LocalDateTime): String = precipitation.type.iconUrl ?: clouds.iconUrl(time)

    fun embedFields(prevWeather: Weather?) = listOfNotNull(
        clouds.describeChange(prevWeather?.clouds)?.let { MessageEmbed.Field("Clouds", it, false) },
        precipitation.type.describeChange(prevWeather?.precipitation?.type)?.let { MessageEmbed.Field("Precipitation", it, false) },
        wind.describeChange(prevWeather?.wind)?.let { MessageEmbed.Field("Wind", it, false) },
    )

    private fun keepWind(precip: PrecipitationWrapper?) = windDuration > 0 && (precip?.type?.isThunder == true || precipitation.type.isThunder)
}
