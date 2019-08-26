package weatherBot.weather.precipitation.snow

import weatherBot.location.Location
import weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

open class Blizzard(date: LocalDate, hours: Long) : HeavySnow(date, hours) {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()

    companion object {
        operator fun invoke(hours: Long, date: LocalDate, temp: Long): HeavySnow {
            return if (Precipitation.thunder()) ThunderBlizzard(hours, date, temp)
            else Blizzard(date, hours)
        }
    }

    override fun fall() { Location.snowLevel += 4 }
}