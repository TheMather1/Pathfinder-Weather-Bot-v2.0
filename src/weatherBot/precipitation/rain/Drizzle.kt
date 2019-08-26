package weatherBot.precipitation.rain

import weatherBot.precipitation.Precipitation
import java.time.LocalDate

class Drizzle(override val hours: Long, override val date: LocalDate) : Rain {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}