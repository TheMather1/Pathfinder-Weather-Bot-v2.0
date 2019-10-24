package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

abstract class Rain(date: LocalDate, hours: Long) : Precipitation(date, hours){
    override fun fall() {}
}