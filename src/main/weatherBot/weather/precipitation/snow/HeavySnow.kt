package weatherBot.weather.precipitation.snow

import weatherBot.d
import weatherBot.location.Location
import weatherBot.weather.Wind
import weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate


open class HeavySnow(override val date: LocalDate, override val hours: Long) : Snow {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()

    companion object{
        fun blizzard(wind: Wind): Boolean = wind >= Wind.SEVERE && (1 d 100) <= 40
        operator fun invoke(hours: Long, date: LocalDate, temp: Long): HeavySnow {
            val wind = Wind()
            fun blizzardDuration(): Long = if ((1 d 100) <= 20) 2 d 12 else hours
            return when {
                Precipitation.thunder() -> Thundersnow(hours, date, temp)
                blizzard(wind) -> Blizzard(blizzardDuration(), date, wind)
                else -> HeavySnow(date, hours)
            }
        }
    }

    override fun fall() { Location.snowLevel += (1 d 4) }
}