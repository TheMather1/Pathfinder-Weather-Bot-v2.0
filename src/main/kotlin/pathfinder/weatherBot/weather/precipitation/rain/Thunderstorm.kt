package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import java.time.LocalDate

class Thunderstorm(override val location: Location, date: LocalDate, hours: Long, override val temp: Long) : HeavyRain(date, hours),
        Thunder {

    override fun print(prev: Precipitation?) = TODO()

    override fun finished() = TODO()


}