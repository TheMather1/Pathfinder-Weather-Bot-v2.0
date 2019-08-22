package weatherBot.precipitation.controller.frozen

import weatherBot.d
import weatherBot.precipitation.HeavySnow
import weatherBot.precipitation.Intensity
import weatherBot.precipitation.Precipitation
import weatherBot.precipitation.controller.Controller
import weatherBot.precipitation.fog.HeavyFog
import weatherBot.precipitation.fog.MediumFog
import weatherBot.precipitation.snow.*

object Heavy: Controller {
    override operator fun invoke(intensity: Intensity): Precipitation = when(1 d 100){
        in 1..10 -> MediumFog(1 d 8)
        in 11..20 -> HeavyFog(2 d 6)
        in 21..60 -> LightSnow(2 d 12)
        in 61..90 -> MediumSnow(1 d 8)
        in 91..100 -> HeavySnow(1 d 6)
        else -> throw RuntimeException("d% returned value not between 1 and 100.")
    }
}