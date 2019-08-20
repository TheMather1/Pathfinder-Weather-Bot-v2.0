package weatherBot.precipitation.controller.frozen

import weatherBot.d
import weatherBot.precipitation.Intensity
import weatherBot.precipitation.controller.Controller
import weatherBot.precipitation.fog.LightFog
import weatherBot.precipitation.fog.MediumFog
import weatherBot.precipitation.snow.LightSnow

object Light: Controller {
    override operator fun invoke(intensity: Intensity){
        when(1 d 100){
            in 1..20 -> LightFog(1 d 6)
            in 21..40 -> LightFog(1 d 8)
            in 41..50 -> MediumFog(1 d 4)
            in 51..60 -> LightSnow(1)
            in 61..75 -> LightSnow(1 d 4)
            in 76..100 -> LightSnow(2 d 12)
        }
    }
}