package pathfinder.weatherBot.weather

import jakarta.persistence.Embeddable
import jakarta.persistence.Enumerated
import jakarta.persistence.EnumType
import pathfinder.diceSyntax.d
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.time.Season
import pathfinder.weatherBot.weather.precipitation.None
import pathfinder.weatherBot.weather.precipitation.PrecipitationWrapper
import pathfinder.weatherBot.weather.precipitation.Thunder
import pathfinder.weatherBot.weather.precipitation.Fog
import pathfinder.weatherBot.weather.precipitation.Fog.*
import pathfinder.weatherBot.weather.precipitation.Snow.*
import pathfinder.weatherBot.weather.precipitation.Rain.*
import java.io.Serializable
import java.time.LocalDateTime

@Embeddable
class Weather(config: GuildConfig, season: Season, time: LocalDateTime, temp: Temperature, prevWeather: Weather?) :
    Serializable {
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

    private var windDuration = 0L
    @Enumerated(EnumType.STRING)
    val wind: Wind = when (precipitation.type) {
        is Fog -> Wind.LIGHT
        HEAVY_SNOW -> precipitation.windOverride
        THUNDERSTORM, THUNDERSNOW, THUNDER_BLIZZARD -> if (prevWeather?.keepWind(precipitation) == true) {
            windDuration = prevWeather.windDuration - 1
            prevWeather.wind
        } else {
            windDuration = 2 + (1 d 3).toLong()
            Thunder.wind
        }
        else -> if (prevWeather?.keepWind(precipitation) == true) {
            windDuration = prevWeather.windDuration - 1
            prevWeather.wind
        } else {
            windDuration = 3 + (1 d 3).toLong()
            Wind()
        }
    }!!

    fun describeChanges(prevWeather: Weather?) = listOfNotNull(
        clouds.describeChange(prevWeather?.clouds),
        precipitation.type.describeChange(prevWeather?.precipitation?.type),
        wind.describeChange(prevWeather?.wind),
    )

    private fun keepWind(precip: PrecipitationWrapper?) = windDuration > 0 && (precip?.type?.isThunder?.not() ?: true || precipitation.type.isThunder)
}
