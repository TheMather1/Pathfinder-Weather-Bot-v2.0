package weatherBot.precipitation.controller.frozen

import weatherBot.d
import weatherBot.dHundredException
import weatherBot.precipitation.Precipitation
import weatherBot.precipitation.controller.Controller
import weatherBot.precipitation.fog.HeavyFog
import weatherBot.precipitation.fog.MediumFog
import weatherBot.precipitation.snow.LightSnow
import weatherBot.precipitation.snow.MediumSnow
import java.time.LocalDate

object Medium: Controller {
    override operator fun invoke(temp: Long, date: LocalDate): Precipitation = when(1 d 100){
            in 1..10 -> MediumFog(1 d 8, date)
            in 11..20 -> MediumFog(1 d 12, date)
            in 21..30 -> HeavyFog(1 d 4, date)
            in 31..50 -> MediumSnow(1 d 4, date)
            in 51..90 -> MediumSnow(1 d 8, date)
            in 91..100 -> LightSnow(2 d 12, date)
            else -> throw dHundredException
        }
}