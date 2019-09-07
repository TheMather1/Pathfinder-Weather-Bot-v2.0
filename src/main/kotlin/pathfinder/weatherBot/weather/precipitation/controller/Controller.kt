package pathfinder.weatherBot.weather.precipitation.controller

import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

interface Controller {
    operator fun invoke(location: Location, temp: Long, date: LocalDate): Precipitation
}