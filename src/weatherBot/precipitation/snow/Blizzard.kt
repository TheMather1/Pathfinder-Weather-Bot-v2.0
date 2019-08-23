package weatherBot.precipitation.snow

import weatherBot.precipitation.Precipitation
import java.time.LocalDate

open class Blizzard(date: LocalDate, hours: Long) : HeavySnow(date, hours) {
    companion object {
        operator fun invoke(hours: Long, date: LocalDate): HeavySnow {
            return if (Precipitation.thunder()) ThunderBlizzard(hours, date)
            else Blizzard(date, hours)
        }
    }
}