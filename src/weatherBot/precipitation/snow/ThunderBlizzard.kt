package weatherBot.precipitation.snow

import weatherBot.precipitation.Precipitation
import weatherBot.precipitation.Thunder
import java.time.LocalDate

class ThunderBlizzard(override val hours: Long, override val date: LocalDate) : Blizzard(date, hours), Thunder{
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}