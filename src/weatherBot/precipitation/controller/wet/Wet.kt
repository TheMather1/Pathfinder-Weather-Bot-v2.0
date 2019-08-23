package weatherBot.precipitation.controller.wet

import weatherBot.location.Location
import weatherBot.precipitation.Intensity
import weatherBot.precipitation.Precipitation
import weatherBot.precipitation.controller.Controller
import java.time.LocalDate

object Wet: Controller {
    override operator fun invoke(temp: Long, date: LocalDate): Precipitation =  when(Location.intensity){
        Intensity.LIGHT -> Light(temp, date)
        Intensity.MEDIUM -> Medium(temp, date)
        Intensity.HEAVY -> Heavy(temp, date)
    }
}