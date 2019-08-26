package weatherBot.precipitation.controller.frozen

import weatherBot.d
import weatherBot.dHundredException
import weatherBot.precipitation.Precipitation
import weatherBot.precipitation.controller.Controller
import weatherBot.precipitation.fog.HeavyFog
import weatherBot.precipitation.fog.MediumFog
import weatherBot.precipitation.snow.HeavySnow
import weatherBot.precipitation.snow.LightSnow
import weatherBot.precipitation.snow.MediumSnow
import java.time.LocalDate

object Heavy: Controller {
    override operator fun invoke(temp: Long, date: LocalDate): Precipitation = when(1 d 100){
        in 1..10 -> MediumFog(1 d 8, date)
        in 11..20 -> HeavyFog(2 d 6, date)
        in 21..60 -> LightSnow(2 d 12, date)
        in 61..90 -> MediumSnow(1 d 8, date)
        in 91..100 -> HeavySnow(1 d 6, date)
        else -> throw dHundredException
    }
}