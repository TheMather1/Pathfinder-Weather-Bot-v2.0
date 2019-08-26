package weatherBot.precipitation.controller.wet

import weatherBot.d
import weatherBot.dHundredException
import weatherBot.precipitation.Precipitation
import weatherBot.precipitation.controller.Controller
import weatherBot.precipitation.fog.LightFog
import weatherBot.precipitation.fog.MediumFog
import weatherBot.precipitation.rain.Drizzle
import weatherBot.precipitation.rain.LightRain
import weatherBot.precipitation.snow.Sleet
import java.time.LocalDate

object Light: Controller {
    override operator fun invoke(temp: Long, date: LocalDate): Precipitation = when(1 d 100){
        in 1..20 -> LightFog(1 d 8, date)
        in 21..40 -> MediumFog(1 d 6, date)
        in 41..50 -> Drizzle(1 d 4, date)
        in 51..75 -> Drizzle(2 d 12, date)
        in 76..90 -> LightRain(1 d 4, date)
        in 91..100 -> if (temp < 40) Sleet(1, date) else LightRain(1, date)
        else -> throw dHundredException
    }
}