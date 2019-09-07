package pathfinder.weatherBot.weather.precipitation.controller.wet

import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Intensity
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.controller.Controller
import java.time.LocalDate

object Wet: Controller {
    override operator fun invoke(location: Location, temp: Long, date: LocalDate): Precipitation =  when(location.intensity){
        Intensity.LIGHT -> Light(location, temp, date)
        Intensity.MEDIUM -> Medium(location, temp, date)
        Intensity.HEAVY -> Heavy(location, temp, date)
    }
}