package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDateTime

abstract class Rain(start: LocalDateTime, end: LocalDateTime) : Precipitation(start, end) {
    override fun fall() {
        TODO()
    }
}