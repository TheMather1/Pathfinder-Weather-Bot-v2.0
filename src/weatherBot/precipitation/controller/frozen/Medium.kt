package weatherBot.precipitation.controller.frozen

import weatherBot.d
import weatherBot.precipitation.Intensity
import weatherBot.precipitation.Precipitation
import weatherBot.precipitation.controller.Controller
import weatherBot.precipitation.fog.HeavyFog
import weatherBot.precipitation.fog.MediumFog
import weatherBot.precipitation.snow.LightSnow
import weatherBot.precipitation.snow.MediumSnow

object Medium: Controller {
    override operator fun invoke(intensity: Intensity): Precipitation = when(1 d 100){
            in 1..10 -> MediumFog(1 d 8)
            in 11..20 -> MediumFog(1 d 12)
            in 21..30 -> HeavyFog(1 d 4)
            in 31..50 -> MediumSnow(1 d 4)
            in 51..90 -> MediumSnow(1 d 8)
            in 91..100 -> LightSnow(2 d 12)
            else -> throw RuntimeException("d% returned value not between 1 and 100.")
        }
}