package pathfinder.weatherBot.weather.precipitation.controller.frozen

import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Intensity
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.controller.Controller
import java.time.LocalDate

object Frozen: Controller {
    override operator fun invoke(location: Location, date: LocalDate, temp: Long): Precipitation = when(location.intensity){
            Intensity.LIGHT -> Light(location, date, temp)
            Intensity.MEDIUM -> Medium(location, date, temp)
            Intensity.HEAVY -> Heavy(location, date, temp)
        }
}