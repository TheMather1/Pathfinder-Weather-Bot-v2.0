package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

open class Blizzard(location: Location, date: LocalDate, hours: Long, open val wind: Wind) : HeavySnow(location, date, hours) {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()

    override fun fall() { location.snowLevel += 4 }
}