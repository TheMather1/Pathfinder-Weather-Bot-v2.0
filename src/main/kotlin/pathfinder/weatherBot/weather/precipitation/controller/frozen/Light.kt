package pathfinder.weatherBot.weather.precipitation.controller.frozen

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.controller.Controller
import pathfinder.weatherBot.weather.precipitation.fog.LightFog
import pathfinder.weatherBot.weather.precipitation.fog.MediumFog
import pathfinder.weatherBot.weather.precipitation.snow.LightSnow
import java.time.LocalDate

object Light: Controller {
    override operator fun invoke(location: Location, date: LocalDate, temp: Long): Precipitation = when(1 d 100){
            in 1..20 -> LightFog(date, 1 d 6)
            in 21..40 -> LightFog(date, 1 d 8)
            in 41..50 -> MediumFog(date, 1 d 4)
            in 51..60 -> LightSnow(location,date, 1)
            in 61..75 -> LightSnow(location,date, 1 d 4)
            in 76..100 -> LightSnow(location,date, 2 d 12)
            else -> throw dHundredException
        }
}