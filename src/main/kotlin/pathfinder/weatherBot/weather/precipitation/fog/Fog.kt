package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

abstract class Fog(date: LocalDate, hours: Long) : Precipitation(date, hours){
    override fun fall() {}
}