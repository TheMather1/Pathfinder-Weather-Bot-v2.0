package weatherBot

import weatherBot.precipitation.Precipitation
import weatherBot.precipitation.Thunder
import weatherBot.precipitation.fog.Fog
import weatherBot.time.Season
import weatherBot.time.TimeFrame
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.math.*

class Weather(season: Season, tempVar: Long, day: LocalDate, private val prevPrecipitation: Precipitation?) {
    fun getPrecipitation(now: LocalDateTime?): Precipitation? = when(now){
        in prevPrecipitation?.timeFrame ?: TimeFrame.empty -> prevPrecipitation
        in precipitation?.timeFrame ?: TimeFrame.empty -> precipitation
        else -> null
    }

    val temp: Long
    val precipitation: Precipitation?
    val clouds: Clouds
    val wind: Wind
    val nightTemp: Long
    fun currentTemp(time: LocalTime): Long = ((temp - nightTemp)/2).let { nightTemp + it + cos((time.hour.toFloat()-12) / 12 * PI).roundToLong() * it }

    init {
        var newTemp = season.temp() + tempVar
        precipitation = Precipitation(season, newTemp, day, prevPrecipitation?.end)
        clouds = if (precipitation == null) Clouds() else Clouds.OVERCAST.also { newTemp += it.adjustTemp(season) }
        temp = newTemp
        wind = when(precipitation){
            is Thunder -> precipitation.wind
            is Fog -> Wind.LIGHT
            else -> Wind()
        }
        nightTemp = temp-(2 d 6)-3
    }
}