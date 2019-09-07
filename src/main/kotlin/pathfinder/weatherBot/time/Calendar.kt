package pathfinder.weatherBot.time

import pathfinder.weatherBot.d
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.time.Day.Companion.LAST_HOUR_OF_DAY
import pathfinder.weatherBot.weather.Clouds
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class Calendar(val location: Location) {
    internal val days = pregenDays()
    val prevPrecipitation: Precipitation?
        get() = days[if (LocalDateTime.now().minusHours(1).hour == 23) 0 else 1].precipitation(LocalTime.now().minusHours(1))
    val precipitation: Precipitation?
        get() = days[1].precipitation(LocalTime.now())
    val weather: Weather
        get() = days[1].weather
    private var tempVarEnd = LocalDate.now()
    private var tempVarDie = { 1 d 1 }

    private fun pregenDays(): MutableList<Day> {
        var precipitationHolder: Precipitation? = null
        var cloudHolder = Clouds.NONE
        var windHolder = Wind.LIGHT
        return MutableList(4) {
            Day(this,
                    LocalDate.now().plusDays((it - 1).toLong()),
                    precipitationHolder,
                    cloudHolder,
                    windHolder
            ).also { day ->
                precipitationHolder = day.weather.precipitation(LAST_HOUR_OF_DAY)
                cloudHolder = day.weather.clouds(LAST_HOUR_OF_DAY)
                windHolder = day.weather.wind(LAST_HOUR_OF_DAY) }
        }
    }
    fun nextDay() {
        val prevWeather = days[3].weather
        days.add(
                Day(
                        this,
                        LocalDate.now().plusDays(3),
                        prevWeather.precipitation,
                        prevWeather.clouds(LAST_HOUR_OF_DAY),
                        prevWeather.wind(LAST_HOUR_OF_DAY)
                )
        )
        days.removeAt(0)
    }

    internal fun tempVar(day: LocalDate): Long = (tempVarDie.takeUnless { day == tempVarEnd }
            ?: location.climate.tempVariation()
                    .apply { tempVarEnd = day.plusDays(second) }
                    .first.apply(::tempVarDie::set)
            ).run { invoke() }
}