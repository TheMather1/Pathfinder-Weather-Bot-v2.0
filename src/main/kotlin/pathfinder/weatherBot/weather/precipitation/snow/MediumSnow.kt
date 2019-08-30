package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

class MediumSnow(override val hours: Long, override val date: LocalDate) : Snow {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()

    override fun fall() { Location.snowLevel += 1 }
}