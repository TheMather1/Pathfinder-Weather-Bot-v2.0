package pathfinder.weatherBot.weather.precipitation.controller.wet

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.controller.Controller
import pathfinder.weatherBot.weather.precipitation.fog.LightFog
import pathfinder.weatherBot.weather.precipitation.fog.MediumFog
import pathfinder.weatherBot.weather.precipitation.rain.Drizzle
import pathfinder.weatherBot.weather.precipitation.rain.LightRain
import pathfinder.weatherBot.weather.precipitation.snow.Sleet

object Light: Controller {
    override operator fun invoke(weather: Weather): Precipitation = when(1 d 100){
        in 1..20 -> LightFog(weather, 1 d 8)
        in 21..40 -> MediumFog(weather, 1 d 6)
        in 41..50 -> Drizzle(weather, 1 d 4)
        in 51..75 -> Drizzle(weather, 2 d 12)
        in 76..90 -> LightRain(weather, 1 d 4)
        in 91..100 -> if (weather.hour.temp < 40) Sleet(weather, 1) else LightRain(weather, 1)
        else -> throw dHundredException
    }
}