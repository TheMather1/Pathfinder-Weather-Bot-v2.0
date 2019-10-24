package pathfinder.weatherBot.weather

import pathfinder.weatherBot.d
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.time.Season
import pathfinder.weatherBot.time.TimeFrame
import java.time.LocalDate
import java.time.LocalTime
import kotlin.math.*

class Weather(val location: Location, private val season: Season, tempVar: Long, day: LocalDate, prevWeather: Weather?) {
    private val prevPrecipitation: Precipitation? = prevWeather?.precipitation
    private val temp = season.temp(location) + tempVar
    private val nightTemp = temp-(2 d 6)-3
    private val prevTemp = prevWeather?.nightTemp ?: nightTemp

    private fun lowTemp(now: LocalTime): Long = if (now > LocalTime.NOON) prevTemp else nightTemp

    fun temp(now: LocalTime): Long = ((temp - lowTemp(now))/2).let { lowTemp(now) + it + cos((now.hour.toFloat()-12) / 12 * PI).roundToLong() * it } + clouds(now).adjustTemp(season)

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

    val precipitation: Precipitation? = Precipitation(location, season, day, prevPrecipitation?.end, temp)
    fun precipitation(now: LocalTime): Precipitation? = when(now){
        in prevPrecipitation?.timeFrame ?: TimeFrame.empty -> prevPrecipitation
        in precipitation?.timeFrame ?: TimeFrame.empty -> precipitation
        else -> null
    }
}