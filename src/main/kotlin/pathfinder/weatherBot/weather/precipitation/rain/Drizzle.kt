package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

class Drizzle(date: LocalDate, hours: Long) : Rain(date, hours) {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}