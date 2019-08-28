package weatherBot.weather.precipitation.snow

import weatherBot.location.Location
import weatherBot.weather.Wind
import weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

open class Blizzard(hours: Long, date: LocalDate, open val wind: Wind) : HeavySnow(date, hours) {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()

    override fun fall() { Location.snowLevel += 4 }
}