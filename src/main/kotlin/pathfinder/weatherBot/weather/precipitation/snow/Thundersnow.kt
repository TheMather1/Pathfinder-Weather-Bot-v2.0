package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import java.time.LocalDate

class Thundersnow(override val date: LocalDate, override val hours: Long, override val temp: Long, override val wind: Wind) : HeavySnow(date, hours),
    Thunder {

    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()


    companion object {
        operator fun invoke(hours: Long, date: LocalDate, temp: Long): HeavySnow {
            val wind = Thunder::wind.call()
            return if (blizzard(wind)) ThunderBlizzard(hours, date, temp, wind)
            else Thundersnow(date, hours, temp, wind)
        }
    }
}