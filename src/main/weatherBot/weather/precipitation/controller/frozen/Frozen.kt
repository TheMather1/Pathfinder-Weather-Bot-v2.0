package weatherBot.weather.precipitation.controller.frozen

import weatherBot.location.Location
import weatherBot.weather.precipitation.Intensity
import weatherBot.weather.precipitation.Precipitation
import weatherBot.weather.precipitation.controller.Controller
import java.time.LocalDate

object Frozen: Controller {
    override operator fun invoke(temp: Long, date: LocalDate): Precipitation = when(Location.intensity){
            Intensity.LIGHT -> Light(temp, date)
            Intensity.MEDIUM -> Medium(temp, date)
            Intensity.HEAVY -> Heavy(temp, date)
        }
}