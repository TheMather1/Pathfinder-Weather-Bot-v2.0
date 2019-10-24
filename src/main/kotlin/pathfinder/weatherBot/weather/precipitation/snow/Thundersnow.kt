package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import java.time.LocalDate

class Thundersnow(location: Location, date: LocalDate, hours: Long, override val temp: Long, override val wind: Wind) : HeavySnow(location, date, hours),
        Thunder {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()


    companion object {
        operator fun invoke(location: Location, hours: Long, date: LocalDate, temp: Long): HeavySnow {
            val wind = Thunder.wind
            return if (blizzard(wind)) ThunderBlizzard(location, date, hours, temp, wind)
            else Thundersnow(location, date, hours, temp, wind)
        }
    }
}