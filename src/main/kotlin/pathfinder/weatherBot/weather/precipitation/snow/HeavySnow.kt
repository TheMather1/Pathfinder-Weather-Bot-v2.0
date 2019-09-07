package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.d
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate


open class HeavySnow(val location: Location, override val date: LocalDate, override val hours: Long) : Snow {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()

    companion object{
        fun blizzard(wind: Wind): Boolean = wind >= Wind.SEVERE && (1 d 100) <= 40
        operator fun invoke(location: Location, hours: Long, date: LocalDate, temp: Long): HeavySnow {
            val wind = Wind()
            fun blizzardDuration(): Long = if ((1 d 100) <= 20) 2 d 12 else hours
            return when {
                Precipitation.thunder() -> Thundersnow(location, hours, date, temp)
                blizzard(wind) -> Blizzard(location, blizzardDuration(), date, wind)
                else -> HeavySnow(location, date, hours)
            }
        }
    }

    override fun fall() { location.snowLevel += (1 d 4) }
}