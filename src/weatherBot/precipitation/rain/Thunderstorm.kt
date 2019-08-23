package weatherBot.precipitation.rain

import weatherBot.Wind
import weatherBot.d
import weatherBot.precipitation.Thunder
import java.lang.RuntimeException
import java.time.LocalDate

class Thunderstorm(override val hours: Long, override val date: LocalDate, temp: Long) : HeavyRain(hours, date), Thunder {
    val wind = when (1 d 100){
        in 1..50 -> Wind.STRONG
        in 51..90 -> Wind.SEVERE
        in 91..100 -> Wind.WINDSTORM
        else -> throw RuntimeException("d% returned value higher than 100.")
    }
    val tornado = wind == Wind.WINDSTORM && (1 d 100) <= 10
    val hurricane = temp > 85 && (1 d 100) <= 20
}