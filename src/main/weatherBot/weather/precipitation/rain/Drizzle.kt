package weatherBot.weather.precipitation.rain

import weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

class Drizzle(override val hours: Long, override val date: LocalDate) : Rain {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}