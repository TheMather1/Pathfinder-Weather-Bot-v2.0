package weatherBot.precipitation.snow

import weatherBot.location.Location
import java.time.LocalDate

class LightSnow(override val hours: Long, override val date: LocalDate) : Snow{
    override fun fall() { Location.snowLevel += 0.5 }
}