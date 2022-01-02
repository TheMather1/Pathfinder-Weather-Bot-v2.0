package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.precipitation.Precipitation

abstract class Rain(hour: Hour, hours: Long) : Precipitation(hour, hours) {
    override fun fall() {
        TODO()
    }
}