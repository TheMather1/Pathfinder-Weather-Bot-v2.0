package pathfinder.weatherBot.weather.precipitation.controller.frozen

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.controller.Controller
import pathfinder.weatherBot.weather.precipitation.fog.HeavyFog
import pathfinder.weatherBot.weather.precipitation.fog.MediumFog
import pathfinder.weatherBot.weather.precipitation.snow.HeavySnow
import pathfinder.weatherBot.weather.precipitation.snow.LightSnow
import pathfinder.weatherBot.weather.precipitation.snow.MediumSnow

object Heavy : Controller {
    override operator fun invoke(weather: Weather): Precipitation = when (1 d 100) {
        in 1..10 -> MediumFog(weather, 1 d 8)
        in 11..20 -> HeavyFog(weather, 2 d 6)
        in 21..60 -> LightSnow(weather, 2 d 12)
        in 61..90 -> MediumSnow(weather, 1 d 8)
        in 91..100 -> HeavySnow(weather, 1 d 6)
        else -> throw dHundredException
    }
}