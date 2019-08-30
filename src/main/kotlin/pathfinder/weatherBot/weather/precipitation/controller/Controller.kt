package pathfinder.weatherBot.weather.precipitation.controller

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

interface Controller {
    operator fun invoke(temp: Long, date: LocalDate): Precipitation
}