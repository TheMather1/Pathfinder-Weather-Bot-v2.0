package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

class MediumFog(date: LocalDate, hours: Long) : Fog(date, hours) {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}