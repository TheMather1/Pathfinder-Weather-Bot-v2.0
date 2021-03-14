package pathfinder.weatherBot.weather.precipitation.controller.frozen

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.controller.Controller
import pathfinder.weatherBot.weather.precipitation.fog.LightFog
import pathfinder.weatherBot.weather.precipitation.fog.MediumFog
import pathfinder.weatherBot.weather.precipitation.snow.LightSnow

object Light: Controller {
    override operator fun invoke(weather: Weather): Precipitation = when(1 d 100){
            in 1..20 -> LightFog(weather, 1 d 6)
            in 21..40 -> LightFog(weather, 1 d 8)
            in 41..50 -> MediumFog(weather, 1 d 4)
            in 51..60 -> LightSnow(weather, 1)
            in 61..75 -> LightSnow(weather, 1 d 4)
            in 76..100 -> LightSnow(weather, 2 d 12)
            else -> throw dHundredException
        }
}