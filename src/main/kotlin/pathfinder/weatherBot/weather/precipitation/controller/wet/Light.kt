package pathfinder.weatherBot.weather.precipitation.controller.wet

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.controller.Controller
import pathfinder.weatherBot.weather.precipitation.fog.LightFog
import pathfinder.weatherBot.weather.precipitation.fog.MediumFog
import pathfinder.weatherBot.weather.precipitation.rain.Drizzle
import pathfinder.weatherBot.weather.precipitation.rain.LightRain
import pathfinder.weatherBot.weather.precipitation.snow.Sleet
import java.time.LocalDate

object Light: Controller {
    override operator fun invoke(location: Location, date: LocalDate, temp: Long): Precipitation = when(1 d 100){
        in 1..20 -> LightFog(date, 1 d 8)
        in 21..40 -> MediumFog(date, 1 d 6)
        in 41..50 -> Drizzle(date, 1 d 4)
        in 51..75 -> Drizzle(date, 2 d 12)
        in 76..90 -> LightRain(date, 1 d 4)
        in 91..100 -> if (temp < 40) Sleet(date, 1) else LightRain(date, 1)
        else -> throw dHundredException
    }
}