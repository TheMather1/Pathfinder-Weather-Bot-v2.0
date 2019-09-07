package pathfinder.weatherBot.weather.precipitation.controller.wet

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.controller.Controller
import pathfinder.weatherBot.weather.precipitation.fog.HeavyFog
import pathfinder.weatherBot.weather.precipitation.rain.HeavyRain
import pathfinder.weatherBot.weather.precipitation.rain.Thunderstorm
import pathfinder.weatherBot.weather.precipitation.snow.Sleet
import java.time.LocalDate

object Heavy: Controller {
    override operator fun invoke(location: Location, temp: Long, date: LocalDate): Precipitation = when(1 d 100){
        in 1..10 -> HeavyFog(1 d 8, date)
        in 11..20 -> HeavyFog(2 d 6, date)
        in 21..50 -> HeavyRain(1 d 12, date)
        in 51..70 -> HeavyRain(2 d 12, date)
        in 71..85 -> if (temp < 40) Sleet(1 d 8, date) else HeavyRain(1 d 8, date)
        in 86..90 -> Thunderstorm(location,1, date, temp)
        in 91..100 -> Thunderstorm(location,1 d 3, date, temp)
        else -> throw dHundredException
    }
}