package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

abstract class Snow(weather: Weather, hours: Long) : Precipitation(weather, hours)