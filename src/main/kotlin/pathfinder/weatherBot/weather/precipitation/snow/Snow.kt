package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDateTime

abstract class Snow(start: LocalDateTime, end: LocalDateTime) : Precipitation(start, end)