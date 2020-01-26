package pathfinder.weatherBot.time

import pathfinder.weatherBot.weather.Weather
import java.io.Serializable
import java.time.LocalTime

class Hour(val day: Day, val time: LocalTime, prevHour: Hour? = null): Serializable{
    var temp = day.temperature.tempAtHour(time)
    val weather: Weather = Weather(this, prevHour?.weather)
}