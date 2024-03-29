package pathfinder.weatherBot.weather

import pathfinder.weatherBot.d
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.time.Season
import pathfinder.weatherBot.weather.precipitation.None
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.snow.HeavySnow
import java.io.Serializable
import java.time.LocalDateTime

class Weather(config: GuildConfig, season: Season, time: LocalDateTime, temp: Temperature, prevWeather: Weather?) :
    Serializable {
    val precipitation: Precipitation =
        prevWeather?.precipitation?.takeIf { time < it.end } ?: Precipitation(config, time, season, temp)

    private var cloudDuration = 0L
    val clouds: Clouds = when {
        precipitation !is None -> Clouds.OVERCAST
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
        is HeavySnow -> precipitation.wind
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

    fun describeChanges(prevWeather: Weather?) = listOfNotNull(
        clouds.describeChange(prevWeather?.clouds),
        precipitation.describeChange(prevWeather?.precipitation),
        wind.describeChange(prevWeather?.wind),
    )

    private fun keepWind(precip: Precipitation?) = windDuration > 0 && (precip !is Thunder || precipitation is Thunder)
}
