package weatherBot.precipitation.snow

import weatherBot.precipitation.Precipitation
import weatherBot.precipitation.Thunder
import java.time.LocalDate

class Thundersnow(override val hours: Long, override val date: LocalDate) : HeavySnow(date, hours), Thunder{
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}