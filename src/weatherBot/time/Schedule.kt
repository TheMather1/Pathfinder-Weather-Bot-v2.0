package weatherBot.time

import weatherBot.Weather
import weatherBot.precipitation.Precipitation
import java.time.LocalDate
import java.time.LocalDateTime

object Schedule {
    private fun pregenDays(): MutableList<Day> {
        var precipitationHolder: Precipitation? = null
        return MutableList(4) {
            Day(LocalDate.now().plusDays((it - 1).toLong()), precipitationHolder).also {day -> precipitationHolder = day.weather.precipitation }
        }
    }
    fun nextDay() {
        days.add(Day(LocalDate.now().plusDays(3), days[3].weather.precipitation))
        days.removeAt(0)
    }
    private val days = pregenDays()
    val prevPrecipitation: Precipitation?
        get() = days[if (LocalDateTime.now().minusHours(1).hour == 23) 0 else 1].getPrecipitation(LocalDateTime.now().minusHours(1))
    val activePrecipitation: Precipitation?
        get() = days[1].getPrecipitation(LocalDateTime.now())
    val weather: Weather
        get() = days[1].weather
}