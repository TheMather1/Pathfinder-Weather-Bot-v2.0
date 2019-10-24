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

object Heavy : Controller {
    override operator fun invoke(location: Location, date: LocalDate, temp: Long): Precipitation = when (1 d 100) {
        in 1..10 -> HeavyFog(date, 1 d 8)
        in 11..20 -> HeavyFog(date, 2 d 6)
        in 21..50 -> HeavyRain(date, 1 d 12)
        in 51..70 -> HeavyRain(date, 2 d 12)
        in 71..85 -> if (temp < 40) Sleet(date, 1 d 8) else HeavyRain(date, 1 d 8)
        in 86..90 -> Thunderstorm(location, date, 1, temp)
        in 91..100 -> Thunderstorm(location, date, 1 d 3, temp)
        else -> throw dHundredException
    }
}