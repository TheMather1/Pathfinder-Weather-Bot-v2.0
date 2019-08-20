package weatherBot.precipitation.controller.frozen

import weatherBot.d
import weatherBot.precipitation.Intensity
import weatherBot.precipitation.controller.Controller
import weatherBot.precipitation.fog.HeavyFog
import weatherBot.precipitation.fog.MediumFog
import weatherBot.precipitation.snow.HeavySnow
import weatherBot.precipitation.snow.LightSnow
import weatherBot.precipitation.snow.MediumSnow

object Heavy: Controller {
    override operator fun invoke(intensity: Intensity){
        when(1 d 100){
            in 1..10 -> MediumFog(1 d 8)
            in 11..20 -> HeavyFog(2 d 6)
            in 21..60 -> LightSnow(2 d 12)
            in 61..90 -> MediumSnow(1 d 8)
            in 91..100 -> TODO("Unresolved bug.") //HeavySnow(1 d 6)
        }
    }
}