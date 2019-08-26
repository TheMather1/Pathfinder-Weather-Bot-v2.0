package weatherBot.precipitation.controller.wet

import weatherBot.d
import weatherBot.dHundredException
import weatherBot.precipitation.Precipitation
import weatherBot.precipitation.controller.Controller
import weatherBot.precipitation.fog.HeavyFog
import weatherBot.precipitation.rain.HeavyRain
import weatherBot.precipitation.rain.Thunderstorm
import weatherBot.precipitation.snow.Sleet
import java.time.LocalDate

object Heavy: Controller {
    override operator fun invoke(temp: Long, date: LocalDate): Precipitation = when(1 d 100){
        in 1..10 -> HeavyFog(1 d 8, date)
        in 11..20 -> HeavyFog(2 d 6, date)
        in 21..50 -> HeavyRain(1 d 12, date)
        in 51..70 -> HeavyRain(2 d 12, date)
        in 71..85 -> if (temp < 40) Sleet(1 d 8, date) else HeavyRain(1 d 8, date)
        in 86..90 -> Thunderstorm(1, date, temp)
        in 91..100 -> Thunderstorm(1 d 3, date, temp)
        else -> throw dHundredException
    }
}