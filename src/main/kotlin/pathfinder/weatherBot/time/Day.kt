package pathfinder.weatherBot.time

import pathfinder.weatherBot.weather.Clouds
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate
import java.time.LocalTime

class Day(private val calendar: Calendar, val day: LocalDate, prevPrecipitation: Precipitation?, prevClouds: Clouds, prevWind: Wind) {
    companion object{
        val LAST_HOUR_OF_DAY: LocalTime = LocalTime.of(23, 0)
    }
    fun precipitation(now: LocalTime): Precipitation? = weather.precipitation(now)

    val season = Season(day)
    val weather =
        Weather(calendar.location, season, calendar.tempVar(day), day, prevPrecipitation, prevClouds, prevWind)
}