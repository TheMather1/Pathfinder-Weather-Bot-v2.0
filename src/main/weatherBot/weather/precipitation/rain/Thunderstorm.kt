package weatherBot.weather.precipitation.rain

import weatherBot.weather.precipitation.Precipitation
import weatherBot.weather.precipitation.Thunder
import weatherBot.weather.precipitation.rain.HeavyRain
import java.time.LocalDate

class Thunderstorm(override val hours: Long, override val date: LocalDate, override val temp: Long) : HeavyRain(hours, date),
    Thunder {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}