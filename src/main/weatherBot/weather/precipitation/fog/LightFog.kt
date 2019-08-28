package weatherBot.weather.precipitation.fog

import weatherBot.weather.precipitation.Precipitation
import weatherBot.weather.precipitation.fog.Fog
import java.time.LocalDate

class LightFog(override val hours: Long, override val date: LocalDate) : Fog {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}