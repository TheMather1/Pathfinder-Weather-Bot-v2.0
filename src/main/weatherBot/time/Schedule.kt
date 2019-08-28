package weatherBot.time

import weatherBot.weather.Clouds
import weatherBot.weather.Weather
import weatherBot.weather.Wind
import weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

object Schedule {
    private val lastHourofDay = LocalTime.of(23, 0)
    private fun pregenDays(): MutableList<Day> {
        var precipitationHolder: Precipitation? = null
        var cloudHolder = Clouds.NONE
        var windHolder = Wind.LIGHT
        return MutableList(4) {
            Day(LocalDate.now().plusDays((it - 1).toLong()), precipitationHolder, cloudHolder, windHolder).also { day ->
                precipitationHolder = day.weather.getPrecipitation(lastHourofDay)
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
    private val days = pregenDays()
    val prevPrecipitation: Precipitation?
        get() = days[if (LocalDateTime.now().minusHours(1).hour == 23) 0 else 1].getPrecipitation(LocalTime.now().minusHours(1))
    val activePrecipitation: Precipitation?
        get() = days[1].getPrecipitation(LocalTime.now())
    val weather: Weather
        get() = days[1].weather
}