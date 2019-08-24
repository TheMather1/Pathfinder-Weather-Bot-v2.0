package weatherBot.precipitation.snow

import weatherBot.location.Location
import java.time.LocalDate

class MediumSnow(override val hours: Long, override val date: LocalDate) : Snow{
    override fun fall() { Location.snowLevel += 1 }
}