package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

class MediumFog(override val hours: Long, override val date: LocalDate) : Fog {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}