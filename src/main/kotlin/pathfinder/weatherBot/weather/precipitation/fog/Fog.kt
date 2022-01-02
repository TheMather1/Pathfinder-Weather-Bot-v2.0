package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.precipitation.Precipitation

abstract class Fog(hour: Hour, hours: Long) : Precipitation(hour, hours) {
    override fun fall() {
        TODO()
    }
}