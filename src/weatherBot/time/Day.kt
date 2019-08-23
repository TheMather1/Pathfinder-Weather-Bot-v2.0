package weatherBot.time

import weatherBot.Weather
import weatherBot.location.Location
import weatherBot.precipitation.Precipitation
import java.time.LocalDate
import java.time.LocalDateTime

class Day(day: LocalDate, prevPrecipitation: Precipitation?) {
    fun getPrecipitation(now: LocalDateTime?): Precipitation? = weather.getPrecipitation(now)

    companion object{
        private lateinit var tempVarEnd: LocalDate
        private lateinit var tempVarDie: () -> Long

        private fun tempVar(day: LocalDate): Long = (tempVarDie.takeUnless { day == tempVarEnd }
            ?: Location.climate.tempVariation()
                .apply { tempVarEnd = day.plusDays(second) }
                .first.apply(::tempVarDie::set)
            ).run { invoke() }
    }

    val season = Season.generate(day)
    val weather = Weather(season, tempVar(day), day, prevPrecipitation?.takeIf { it.end.toLocalDate() >= day })
}