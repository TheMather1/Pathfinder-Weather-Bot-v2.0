package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

class MediumRain(date: LocalDate, hours: Long) : Rain(date, hours) {
    override val fireRetardance = 15

    override fun print(prev: Precipitation?) = TODO()

    override fun finished() = TODO()
}