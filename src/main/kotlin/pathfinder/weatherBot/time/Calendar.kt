package pathfinder.weatherBot.time

import pathfinder.weatherBot.d
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate
import java.time.LocalTime
import java.time.temporal.ChronoUnit.HOURS

class Calendar(val location: Location) {
    internal val days = pregenDays()
    val precipitation
        get() = days[1].precipitation(LocalTime.now().truncatedTo(HOURS))
    val weather
        get() = days[1].weather
    private var tempVarEnd = LocalDate.now()
    private var tempVarDie = { 1 d 1 }

    private fun pregenDays(): MutableList<Day> = (0..3).fold(ArrayList<Day>()) { dayList, x ->
        dayList.also { it + Day(this, LocalDate.now().plusDays((x - 1).toLong()), dayList.lastOrNull()) }
    }

    fun nextDay() {
        days.add(
                Day(this, LocalDate.now().plusDays(3), days[3])
        )
        days.removeAt(0)
    }

    internal fun tempVar(day: LocalDate) = (tempVarDie.takeUnless { day == tempVarEnd }
            ?: location.climate.tempVariation()
                    .apply { tempVarEnd = day.plusDays(second) }
                    .first.apply(::tempVarDie::set)
            )()
}