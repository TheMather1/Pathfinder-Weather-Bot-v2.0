package weatherBot.weather.precipitation.snow

import weatherBot.weather.precipitation.Precipitation
import weatherBot.weather.precipitation.Thunder
import java.time.LocalDate

class Thundersnow(override val hours: Long, override val date: LocalDate, override val temp: Long) : HeavySnow(date, hours), Thunder{
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}