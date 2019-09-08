package pathfinder.weatherBot.time

import pathfinder.weatherBot.d
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate
import java.time.LocalTime

class Calendar(val location: Location) {
    internal val days = pregenDays()
    val precipitation: Precipitation?
        get() = days[1].precipitation(LocalTime.now())
    val weather: Weather
        get() = days[1].weather
    private var tempVarEnd = LocalDate.now()
    private var tempVarDie = { 1 d 1 }

    private fun pregenDays(): MutableList<Day> {
        var weatherHolder: Weather? = null
        return MutableList(4) {
            Day(this, LocalDate.now().plusDays((it - 1).toLong()), weatherHolder)
                    .also { day -> weatherHolder = day.weather}
        }
    }
    fun nextDay() {
        val prevWeather = days[3].weather
        days.add(
                Day(this, LocalDate.now().plusDays(3), prevWeather)
        )
        days.removeAt(0)
    }

    internal fun tempVar(day: LocalDate): Long = (tempVarDie.takeUnless { day == tempVarEnd }
            ?: location.climate.tempVariation()
                    .apply { tempVarEnd = day.plusDays(second) }
                    .first.apply(::tempVarDie::set)
            ).run { invoke() }
}