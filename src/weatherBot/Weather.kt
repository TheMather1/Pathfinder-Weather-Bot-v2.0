package weatherBot

import weatherBot.precipitation.Precipitation
import weatherBot.time.Season
import weatherBot.time.TimeFrame
import java.time.LocalDate
import java.time.LocalDateTime

class Weather(season: Season, tempVar: Long, day: LocalDate, private val prevPrecipitation: Precipitation?) {
    fun getPrecipitation(now: LocalDateTime?): Precipitation? = when(now){
        in prevPrecipitation?.timeFrame ?: TimeFrame.empty -> prevPrecipitation
        in precipitation?.timeFrame ?: TimeFrame.empty -> precipitation
        else -> null
    }

    val temp: Long
    val precipitation: Precipitation?
    val clouds: Clouds

    init {
        var newTemp = season.temp() + tempVar
        precipitation = Precipitation(season, newTemp, day, prevPrecipitation?.end)
        clouds = if (precipitation == null) Clouds() else Clouds.OVERCAST.also { newTemp += it.adjustTemp(season) }
        temp = newTemp
    }
}