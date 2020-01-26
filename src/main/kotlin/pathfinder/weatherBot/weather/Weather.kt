package pathfinder.weatherBot.weather

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import pathfinder.weatherBot.weather.precipitation.fog.Fog

class Weather(val hour: Hour, prevWeather: Weather?) {
    val precipitation: Precipitation? = prevWeather?.precipitation?.next(this) ?: Precipitation(this)
    val clouds = if(precipitation != null) Clouds.OVERCAST else Clouds().also { hour.temp += it.adjustTemp(hour.day.season) }
    val wind = when (precipitation) {
        is Thunder -> precipitation.wind
        is Fog -> Wind.LIGHT
        else -> Wind()
    }
}