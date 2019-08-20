package weatherBot.precipitation.controller.wet

import weatherBot.precipitation.Intensity
import weatherBot.precipitation.controller.Controller
import weatherBot.precipitation.controller.frozen.Heavy
import weatherBot.precipitation.controller.frozen.Light
import weatherBot.precipitation.controller.frozen.Medium

object Wet: Controller {
    override operator fun invoke(intensity: Intensity){
        when(intensity){
            Intensity.LIGHT -> Light(intensity)
            Intensity.MEDIUM -> Medium(intensity)
            Intensity.HEAVY -> Heavy(intensity)
        }
    }
}