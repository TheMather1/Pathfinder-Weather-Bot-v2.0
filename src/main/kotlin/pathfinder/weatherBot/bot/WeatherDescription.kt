package pathfinder.weatherBot.bot

import pathfinder.weatherBot.weather.Clouds
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalTime

class WeatherDescription(val precipitation: Precipitation?, val clouds: Clouds, val wind: Wind, time: LocalTime)