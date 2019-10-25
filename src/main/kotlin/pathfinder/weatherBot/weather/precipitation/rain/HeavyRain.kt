package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

open class HeavyRain(date: LocalDate, hours: Long) : Rain(date, hours) {
    override val fireRetardance = 25

    override fun fall() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun print(prev: Precipitation?) = TODO()

    override fun finished() = TODO()
}