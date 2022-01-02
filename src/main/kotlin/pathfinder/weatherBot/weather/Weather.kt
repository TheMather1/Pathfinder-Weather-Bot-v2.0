package pathfinder.weatherBot.weather

import pathfinder.weatherBot.d
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import java.io.Serializable

class Weather(hour: Hour, prevWeather: Weather?) : Serializable {
    val precipitation: Precipitation? = prevWeather?.precipitation?.next(hour) ?: Precipitation(hour)

    private var cloudDuration = 0L
    val clouds: Clouds = when {
        precipitation != null -> Clouds.OVERCAST
        prevWeather != null && prevWeather.cloudDuration > 0 -> {
            cloudDuration = prevWeather.cloudDuration - 1
            prevWeather.clouds.also { hour.temp += it.adjustTemp(hour.day.season) }
        }
        else -> {
            cloudDuration = 2 + (1 d 3)
            Clouds().also { hour.temp += it.adjustTemp(hour.day.season) }
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
            precipitation.wind
        }
        else -> if (prevWeather?.keepWind(precipitation) == true) {
            windDuration = prevWeather.windDuration - 1
            prevWeather.wind
        } else {
            windDuration = 3 + (1 d 3)
            Wind()
        }
    }

    val description = (clouds.print(prevWeather?.clouds)?.plus("\n")
        ?: "") + ((
            if (precipitation == null) prevWeather?.precipitation?.finished
            else precipitation.description(prevWeather?.precipitation)
            )?.plus("\n")
        ?: "") + (wind.print(prevWeather?.wind) ?: "")

    private fun keepWind(precip: Precipitation?) = windDuration > 0 && (precip !is Thunder || precipitation is Thunder)
}