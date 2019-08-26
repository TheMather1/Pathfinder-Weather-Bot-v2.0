package weatherBot.precipitation.snow

import weatherBot.location.Location
import weatherBot.precipitation.Precipitation
import java.time.LocalDate

open class Blizzard(date: LocalDate, hours: Long) : HeavySnow(date, hours) {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()

    companion object {
        operator fun invoke(hours: Long, date: LocalDate): HeavySnow {
            return if (Precipitation.thunder()) ThunderBlizzard(hours, date)
            else Blizzard(date, hours)
        }
    }

    override fun fall() { Location.snowLevel += 4 }
}