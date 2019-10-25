package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

class LightRain(date: LocalDate, hours: Long) : Rain(date, hours) {
    override val fireRetardance = 10

    override fun print(prev: Precipitation?) = TODO()

    override fun finished() = TODO()
}