package weatherBot.weather.precipitation.controller.frozen

import weatherBot.d
import weatherBot.dHundredException
import weatherBot.weather.precipitation.Precipitation
import weatherBot.weather.precipitation.controller.Controller
import weatherBot.weather.precipitation.fog.LightFog
import weatherBot.weather.precipitation.fog.MediumFog
import weatherBot.weather.precipitation.snow.LightSnow
import java.time.LocalDate

object Light: Controller {
    override operator fun invoke(temp: Long, date: LocalDate): Precipitation = when(1 d 100){
            in 1..20 -> LightFog(1 d 6, date)
            in 21..40 -> LightFog(1 d 8, date)
            in 41..50 -> MediumFog(1 d 4, date)
            in 51..60 -> LightSnow(1, date)
            in 61..75 -> LightSnow(1 d 4, date)
            in 76..100 -> LightSnow(2 d 12, date)
            else -> throw dHundredException
        }
}