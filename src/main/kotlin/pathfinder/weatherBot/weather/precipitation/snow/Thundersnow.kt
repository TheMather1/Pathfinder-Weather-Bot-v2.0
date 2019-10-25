package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import java.time.LocalDate

class Thundersnow(location: Location, date: LocalDate, hours: Long, override val temp: Long, override val wind: Wind) : HeavySnow(location, date, hours),
        Thunder {
    override fun print(prev: Precipitation?) = TODO()

    override fun finished() = TODO()


    companion object {
        operator fun invoke(location: Location, hours: Long, date: LocalDate, temp: Long): HeavySnow = Thunder.wind.let {
            if (blizzard(it)) ThunderBlizzard(location, date, hours, temp, it)
            else Thundersnow(location, date, hours, temp, it)
        }
    }
}