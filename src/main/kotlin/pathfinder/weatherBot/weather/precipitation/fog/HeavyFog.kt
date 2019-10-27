package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

class HeavyFog(date: LocalDate, hours: Long) : Fog(date, hours) {
    override val fireRetardance = 10

    override fun print(prev: Precipitation?) = TODO()

    override fun finished() = TODO()
}