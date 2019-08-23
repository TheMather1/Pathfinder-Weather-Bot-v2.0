package weatherBot.precipitation.snow

import weatherBot.precipitation.Thunder
import java.time.LocalDate

class Thundersnow(override val hours: Long, override val date: LocalDate) : HeavySnow(date, hours), Thunder {
}