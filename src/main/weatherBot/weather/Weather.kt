package weatherBot.weather

import weatherBot.d
import weatherBot.weather.precipitation.Precipitation
import weatherBot.weather.precipitation.Thunder
import weatherBot.weather.precipitation.fog.Fog
import weatherBot.time.Season
import weatherBot.time.TimeFrame
import java.time.LocalDate
import java.time.LocalTime
import kotlin.math.*

class Weather(private val season: Season, tempVar: Long, day: LocalDate, private val prevPrecipitation: Precipitation?, private val prevClouds: Clouds, private val prevWind: Wind) {

    private val temp = season.temp() + tempVar
    private val nightTemp = temp-(2 d 6)-3
    fun temp(now: LocalTime): Long = ((temp - nightTemp)/2).let { nightTemp + it + cos((now.hour.toFloat()-12) / 12 * PI).roundToLong() * it } + clouds(now).adjustTemp(season)

    val clouds = Clouds()
    fun clouds(time: LocalTime): Clouds {
        return when (time) {
            in (precipitation?.timeFrame ?: TimeFrame.empty) -> Clouds.OVERCAST
            in (prevPrecipitation?.timeFrame ?: TimeFrame.empty) -> Clouds.OVERCAST
            else -> precipitation?.timeFrame?.run {
                clouds + (Clouds.values().size - min(
                    time.compareTo(start.toLocalTime()).coerceAtLeast(0),
                    end.toLocalTime().compareTo(time).coerceAtLeast(0)
                ) - 1)
            } ?: clouds
        }
    }

    val wind = Wind()
    fun wind(time: LocalTime) = when{
            precipitation is Thunder && time in precipitation.timeFrame -> precipitation.wind
            precipitation is Fog && time in precipitation.timeFrame -> Wind.LIGHT
            else -> wind
    }

    val precipitation = Precipitation(season, temp, day, prevPrecipitation?.end)
    fun precipitation(now: LocalTime): Precipitation? = when(now){
        in prevPrecipitation?.timeFrame ?: TimeFrame.empty -> prevPrecipitation
        in precipitation?.timeFrame ?: TimeFrame.empty -> precipitation
        else -> null
    }
}