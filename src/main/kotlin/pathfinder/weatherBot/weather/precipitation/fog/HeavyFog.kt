package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import java.time.LocalDate

class HeavyFog(override val hours: Long, override val date: LocalDate) : Fog {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}