package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.d
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate


open class HeavySnow(val location: Location, date: LocalDate, hours: Long) : Snow(date, hours) {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()

    companion object{
        fun blizzard(wind: Wind): Boolean = wind >= Wind.SEVERE && (1 d 100) <= 40
        operator fun invoke(location: Location, date: LocalDate, hours: Long, temp: Long): HeavySnow {
            val wind = Wind()
            fun blizzardDuration(): Long = if ((1 d 100) <= 20) 2 d 12 else hours
            return when {
                thunder() -> Thundersnow(location, hours, date, temp)
                blizzard(wind) -> Blizzard(location, date, blizzardDuration(), wind)
                else -> HeavySnow(location, date, hours)
            }
        }
    }

    override fun fall() { location.snowLevel += (1 d 4) }
}