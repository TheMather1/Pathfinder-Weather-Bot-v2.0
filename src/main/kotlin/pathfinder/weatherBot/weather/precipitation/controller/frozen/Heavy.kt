package pathfinder.weatherBot.weather.precipitation.controller.frozen

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.controller.Controller
import pathfinder.weatherBot.weather.precipitation.fog.HeavyFog
import pathfinder.weatherBot.weather.precipitation.fog.MediumFog
import pathfinder.weatherBot.weather.precipitation.snow.HeavySnow
import pathfinder.weatherBot.weather.precipitation.snow.LightSnow
import pathfinder.weatherBot.weather.precipitation.snow.MediumSnow
import java.time.LocalDate

object Heavy: Controller {
    override operator fun invoke(location: Location, temp: Long, date: LocalDate): Precipitation = when(1 d 100){
        in 1..10 -> MediumFog(1 d 8, date)
        in 11..20 -> HeavyFog(2 d 6, date)
        in 21..60 -> LightSnow(location,2 d 12, date)
        in 61..90 -> MediumSnow(location,1 d 8, date)
        in 91..100 -> HeavySnow(location,1 d 6, date, temp)
        else -> throw dHundredException
    }
}