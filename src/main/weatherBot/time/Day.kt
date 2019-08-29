package weatherBot.time

import weatherBot.d
import weatherBot.weather.Weather
import weatherBot.location.Location
import weatherBot.weather.Clouds
import weatherBot.weather.Wind
import weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

val lastHourofDay: LocalTime = LocalTime.of(23, 0)

class Day(val day: LocalDate, prevPrecipitation: Precipitation?, prevClouds: Clouds, prevWind: Wind) {
    fun precipitation(now: LocalTime): Precipitation? = weather.precipitation(now)

    companion object{
        internal val days = pregenDays()
        val prevPrecipitation: Precipitation?
            get() = days[if (LocalDateTime.now().minusHours(1).hour == 23) 0 else 1].precipitation(LocalTime.now().minusHours(1))
        val precipitation: Precipitation?
            get() = days[1].precipitation(LocalTime.now())
        val weather: Weather
            get() = days[1].weather
        private var tempVarEnd = LocalDate.now()
        private var tempVarDie = { 1 d 1 }

        private fun tempVar(day: LocalDate): Long = (tempVarDie.takeUnless { day == tempVarEnd }
            ?: Location.climate.tempVariation()
                .apply { tempVarEnd = day.plusDays(second) }
                .first.apply(Companion::tempVarDie::set)
            ).run { invoke() }


        private fun pregenDays(): MutableList<Day> {
            var precipitationHolder: Precipitation? = null
            var cloudHolder = Clouds.NONE
            var windHolder = Wind.LIGHT
            return MutableList(4) {
                Day(LocalDate.now().plusDays((it - 1).toLong()), precipitationHolder, cloudHolder, windHolder).also { day ->
                    precipitationHolder = day.weather.precipitation(lastHourofDay)
                    cloudHolder = day.weather.clouds(lastHourofDay)
                    windHolder = day.weather.wind(lastHourofDay) }
            }
        }
        fun nextDay() {
            val prevWeather = days[3].weather
            days.add(
                Day(
                    LocalDate.now().plusDays(3),
                    prevWeather.precipitation,
                    prevWeather.clouds(lastHourofDay),
                    prevWeather.wind(lastHourofDay)
                )
            )
            days.removeAt(0)
        }
    }

    val season = Season(day)
    val weather =
        Weather(season, tempVar(day), day, prevPrecipitation, prevClouds, prevWind)
}