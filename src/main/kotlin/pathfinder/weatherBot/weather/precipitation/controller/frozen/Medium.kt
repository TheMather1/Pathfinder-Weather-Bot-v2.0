package pathfinder.weatherBot.weather.precipitation.controller.frozen

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.controller.Controller
import pathfinder.weatherBot.weather.precipitation.fog.HeavyFog
import pathfinder.weatherBot.weather.precipitation.fog.MediumFog
import pathfinder.weatherBot.weather.precipitation.snow.LightSnow
import pathfinder.weatherBot.weather.precipitation.snow.MediumSnow
import java.time.LocalDate

object Medium: Controller {
    override operator fun invoke(location: Location, date: LocalDate, temp: Long): Precipitation = when(1 d 100){
            in 1..10 -> MediumFog(date, 1 d 8)
            in 11..20 -> MediumFog(date, 1 d 12)
            in 21..30 -> HeavyFog(date, 1 d 4)
            in 31..50 -> MediumSnow(location,date, 1 d 4)
            in 51..90 -> MediumSnow(location,date, 1 d 8)
            in 91..100 -> LightSnow(location,date, 2 d 12)
            else -> throw dHundredException
        }
}