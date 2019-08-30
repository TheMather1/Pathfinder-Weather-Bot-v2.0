package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

open class Blizzard(hours: Long, date: LocalDate, open val wind: Wind) : HeavySnow(date, hours) {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()

    override fun fall() { Location.snowLevel += 4 }
}