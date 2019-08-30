package pathfinder.weatherBot.weather.precipitation.controller.frozen

import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Intensity
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.controller.Controller
import java.time.LocalDate

object Frozen: Controller {
    override operator fun invoke(temp: Long, date: LocalDate): Precipitation = when(Location.intensity){
            Intensity.LIGHT -> Light(temp, date)
            Intensity.MEDIUM -> Medium(temp, date)
            Intensity.HEAVY -> Heavy(temp, date)
        }
}