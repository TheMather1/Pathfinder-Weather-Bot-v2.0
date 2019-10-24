package pathfinder.weatherBot.weather.precipitation.controller.wet

import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Intensity
import pathfinder.weatherBot.weather.precipitation.Intensity.*
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.controller.Controller
import java.time.LocalDate

object Wet: Controller {
    override operator fun invoke(location: Location, date: LocalDate, temp: Long): Precipitation =  when(location.intensity){
        LIGHT -> Light(location, date, temp)
        MEDIUM -> Medium(location, date, temp)
        HEAVY -> Heavy(location, date, temp)
    }
}