package pathfinder.weatherBot.weather.precipitation.controller.wet

import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Intensity
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.controller.Controller
import pathfinder.weatherBot.weather.precipitation.controller.wet.Heavy
import pathfinder.weatherBot.weather.precipitation.controller.wet.Light
import pathfinder.weatherBot.weather.precipitation.controller.wet.Medium
import java.time.LocalDate

object Wet: Controller {
    override operator fun invoke(temp: Long, date: LocalDate): Precipitation =  when(Location.intensity){
        Intensity.LIGHT -> Light(temp, date)
        Intensity.MEDIUM -> Medium(temp, date)
        Intensity.HEAVY -> Heavy(temp, date)
    }
}