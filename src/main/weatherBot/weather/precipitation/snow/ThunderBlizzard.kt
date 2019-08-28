package weatherBot.weather.precipitation.snow

import weatherBot.weather.Wind
import weatherBot.weather.precipitation.Precipitation
import weatherBot.weather.precipitation.Thunder
import weatherBot.weather.precipitation.snow.Blizzard
import java.time.LocalDate

class ThunderBlizzard(override val hours: Long, override val date: LocalDate, override val temp: Long, override val wind: Wind) : Blizzard(hours, date, wind),
    Thunder {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}