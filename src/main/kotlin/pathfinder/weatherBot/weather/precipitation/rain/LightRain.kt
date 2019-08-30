package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

class LightRain(override val hours: Long, override val date: LocalDate) : Rain {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}