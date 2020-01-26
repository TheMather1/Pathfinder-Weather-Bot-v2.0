package pathfinder.weatherBot.weather.precipitation.controller.wet

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.controller.Controller
import pathfinder.weatherBot.weather.precipitation.fog.HeavyFog
import pathfinder.weatherBot.weather.precipitation.rain.HeavyRain
import pathfinder.weatherBot.weather.precipitation.rain.Thunderstorm
import pathfinder.weatherBot.weather.precipitation.snow.Sleet

object Heavy : Controller {
    override operator fun invoke(weather: Weather): Precipitation = when (1 d 100) {
        in 1..10 -> HeavyFog(weather, 1 d 8)
        in 11..20 -> HeavyFog(weather, 2 d 6)
        in 21..50 -> HeavyRain(weather, 1 d 12)
        in 51..70 -> HeavyRain(weather, 2 d 12)
        in 71..85 -> if (weather.hour.temp < 40) Sleet(weather, 1 d 8) else HeavyRain(weather, 1 d 8)
        in 86..90 -> Thunderstorm(weather, 1)
        in 91..100 -> Thunderstorm(weather, 1 d 3)
        else -> throw dHundredException
    }
}