package weatherBot.time

import weatherBot.weather.Weather
import weatherBot.location.Location
import weatherBot.weather.Clouds
import weatherBot.weather.Wind
import weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate
import java.time.LocalTime

class Day(day: LocalDate, prevPrecipitation: Precipitation?, prevClouds: Clouds, prevWind: Wind) {
    fun getPrecipitation(now: LocalTime): Precipitation? = weather.getPrecipitation(now)

    companion object{
        private lateinit var tempVarEnd: LocalDate
        private lateinit var tempVarDie: () -> Long

        private fun tempVar(day: LocalDate): Long = (tempVarDie.takeUnless { day == tempVarEnd }
            ?: Location.climate.tempVariation()
                .apply { tempVarEnd = day.plusDays(second) }
                .first.apply(Companion::tempVarDie::set)
            ).run { invoke() }
    }

    val season = Season.generate(day)
    val weather =
        Weather(season, tempVar(day), day, prevPrecipitation, prevClouds, prevWind)
}