package weatherBot.precipitation.snow

import weatherBot.d
import weatherBot.location.Location
import weatherBot.precipitation.Precipitation
import java.time.LocalDate


open class HeavySnow(override val date: LocalDate, override val hours: Long) : Snow {
    companion object{
        private fun blizzard(): Boolean = (1 d 100) <= 40
        operator fun invoke(hours: Long, date: LocalDate): HeavySnow {
            fun blizzardDuration(): Long = if ((1 d 100) <= 20) 2 d 12 else hours
            return when {
                blizzard() -> Blizzard(blizzardDuration(), date)
                Precipitation.thunder() -> Thundersnow(hours, date)
                else -> HeavySnow(date, hours)
            }
        }
    }

    override fun fall() { Location.snowLevel += (1 d 4) }
}