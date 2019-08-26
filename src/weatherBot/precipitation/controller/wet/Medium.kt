package weatherBot.precipitation.controller.wet

import weatherBot.d
import weatherBot.dHundredException
import weatherBot.precipitation.Precipitation
import weatherBot.precipitation.controller.Controller
import weatherBot.precipitation.fog.HeavyFog
import weatherBot.precipitation.fog.MediumFog
import weatherBot.precipitation.rain.MediumRain
import weatherBot.precipitation.snow.Sleet
import java.time.LocalDate

object Medium: Controller {
    override operator fun invoke(temp: Long, date: LocalDate): Precipitation = when(1 d 100){
        in 1..10 -> MediumFog(1 d 8, date)
        in 11..20 -> MediumFog(1 d 12, date)
        in 21..30 -> HeavyFog(1 d 4, date)
        in 31..35 -> MediumRain(1 d 4, date)
        in 36..70 -> MediumRain(1 d 8, date)
        in 71..90 -> MediumRain(2 d 12, date)
        in 91..100 -> if (temp < 40) Sleet(1 d 4, date) else MediumRain(1 d 4, date)
        else -> throw dHundredException
    }
}