package pathfinder.weatherBot.time

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate
import java.time.LocalTime

class Day(calendar: Calendar, val day: LocalDate, prevWeather: Weather?) {
    fun precipitation(now: LocalTime): Precipitation? = weather.precipitation(now)

    val weather = Weather(calendar.location, Season(day), calendar.tempVar(day), day, prevWeather)
}