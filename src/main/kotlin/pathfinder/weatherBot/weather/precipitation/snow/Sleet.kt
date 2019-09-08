package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

class Sleet(override val hours: Long, override val date: LocalDate) : Snow {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}