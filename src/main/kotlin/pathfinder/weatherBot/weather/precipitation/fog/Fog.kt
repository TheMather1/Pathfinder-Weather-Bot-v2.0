package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDateTime

abstract class Fog(start: LocalDateTime, end: LocalDateTime) : Precipitation(start, end) {
    override fun fall() {
        TODO()
    }
}