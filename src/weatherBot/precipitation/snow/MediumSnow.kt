package weatherBot.precipitation.snow

import weatherBot.location.Location
import weatherBot.precipitation.Precipitation
import java.time.LocalDate

class MediumSnow(override val hours: Long, override val date: LocalDate) : Snow{
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()

    override fun fall() { Location.snowLevel += 1 }
}