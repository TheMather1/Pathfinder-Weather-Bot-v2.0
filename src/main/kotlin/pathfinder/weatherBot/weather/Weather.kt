package pathfinder.weatherBot.weather

import pathfinder.weatherBot.d
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.time.Season
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import java.io.Serializable
import java.time.LocalDateTime

class Weather(config: GuildConfig, season: Season, time: LocalDateTime, temp: Temperature, prevWeather: Weather?) :
    Serializable {
    val precipitation: Precipitation? =
        prevWeather?.precipitation?.takeIf { time < it.end } ?: Precipitation(config, time, season, temp)

    private var cloudDuration = 0L
    val clouds: Clouds = when {
        precipitation != null -> Clouds.OVERCAST
        prevWeather != null && prevWeather.cloudDuration > 0 -> {
            cloudDuration = prevWeather.cloudDuration - 1
            prevWeather.clouds.also { temp.temp += it.adjustTemp(season) }
        }
        else -> {
            cloudDuration = 2 + (1 d 3)
            Clouds().also { temp.temp += it.adjustTemp(season) }
        }
    }

    private var windDuration = 0L
    val wind: Wind = when (precipitation) {
        is Fog -> Wind.LIGHT
        is Thunder -> if (prevWeather?.keepWind(precipitation) == true) {
            windDuration = prevWeather.windDuration - 1
            prevWeather.wind
        } else {
            windDuration = 2 + (1 d 3)
            Thunder.wind
        }
        else -> if (prevWeather?.keepWind(precipitation) == true) {
            windDuration = prevWeather.windDuration - 1
            prevWeather.wind
        } else {
            windDuration = 3 + (1 d 3)
            Wind()
        }
    }

    val descriptions = listOfNotNull(
        clouds.print(prevWeather?.clouds),
        if (precipitation == null) prevWeather?.precipitation?.finished
        else precipitation.description(prevWeather?.precipitation),
        wind.print(prevWeather?.wind),
    )

    private fun keepWind(precip: Precipitation?) = windDuration > 0 && (precip !is Thunder || precipitation is Thunder)
}
