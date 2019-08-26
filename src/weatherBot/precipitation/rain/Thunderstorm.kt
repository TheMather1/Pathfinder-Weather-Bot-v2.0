package weatherBot.precipitation.rain

import weatherBot.Wind
import weatherBot.d
import weatherBot.precipitation.Precipitation
import weatherBot.precipitation.Thunder
import java.time.LocalDate

class Thunderstorm(override val hours: Long, override val date: LocalDate, temp: Long) : HeavyRain(hours, date), Thunder {
    val tornado = wind == Wind.WINDSTORM && (1 d 100) <= 10
    val hurricane = temp > 85 && (1 d 100) <= 20

    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}