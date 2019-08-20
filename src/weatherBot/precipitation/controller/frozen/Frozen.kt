package weatherBot.precipitation.controller.frozen

import weatherBot.precipitation.Intensity
import weatherBot.precipitation.controller.Controller

object Frozen: Controller {
    override operator fun invoke(intensity: Intensity){
        when(intensity){
            Intensity.LIGHT -> Light(intensity)
            Intensity.MEDIUM -> Medium(intensity)
            Intensity.HEAVY -> Heavy(intensity)
        }
    }
}