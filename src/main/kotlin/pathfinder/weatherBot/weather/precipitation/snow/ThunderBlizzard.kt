package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import java.time.LocalDate

class ThunderBlizzard(location: pathfinder.weatherBot.location.Location, override val hours: Long, override val date: LocalDate, override val temp: Long, override val wind: Wind) : Blizzard(location, hours, date, wind),
    Thunder {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}