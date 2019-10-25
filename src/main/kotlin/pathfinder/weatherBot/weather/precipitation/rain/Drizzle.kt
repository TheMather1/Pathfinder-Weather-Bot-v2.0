package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

class Drizzle(date: LocalDate, hours: Long) : Rain(date, hours) {
    override val fireRetardance = 5

    override fun print(prev: Precipitation?) = TODO()

    override fun finished() = TODO()
}