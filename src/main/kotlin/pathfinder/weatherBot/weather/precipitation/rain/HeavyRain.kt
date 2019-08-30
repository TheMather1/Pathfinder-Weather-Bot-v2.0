package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

open class HeavyRain(override val hours: Long, override val date: LocalDate): Rain {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}