package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.precipitation.Precipitation

abstract class Snow(hour: Hour, hours: Long) : Precipitation(hour, hours)