package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

abstract class Snow(date: LocalDate, hours: Long) : Precipitation(date, hours)