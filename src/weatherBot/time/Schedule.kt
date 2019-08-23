package weatherBot.time

import weatherBot.precipitation.Precipitation
import java.time.LocalDate
import java.time.LocalDateTime

object Schedule {
    var forecastDate = LocalDate.now().plusDays(3)
    fun day(): Day = Day(forecastDate, null)
    val activePrecipitation: Precipitation?
        get() = day().getPrecipitation(LocalDateTime.now())
}