package pathfinder.weatherBot.time

import pathfinder.weatherBot.weather.Clouds
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDateTime

class Hour(private val calendar: Calendar, val dateTime: LocalDateTime, val precipitation: Precipitation?, val clouds: Clouds, val wind: Wind, val temp: Long) {
    private constructor(calendar: Calendar, dateTime: LocalDateTime) : this(calendar, dateTime, calendar.days[dateTime.toLocalDate().compareTo(calendar.days[1].day) + 1].weather)
    private constructor(calendar: Calendar, dateTime: LocalDateTime, weather: Weather) : this(
        calendar,
        dateTime,
        weather.precipitation,
        weather.clouds,
        weather.wind,
        weather.temp(dateTime.toLocalTime())
    )

    val print: String
        get(){
            val precipitationDescription = precipitation?.print(prevHour.precipitation) ?: prevHour.precipitation?.finished()
            val cloudDescription = clouds.print(prevHour.clouds)
            val windDescription = wind.print(prevHour.wind)
            TODO("Description formatting.")
        }

    private val prevHour: Hour
            get() = Hour(calendar, dateTime.minusHours(1))

    private val extremeTempDescription: String?
        get() = when(temp + (precipitation?.tempAdjust ?: 0)){
            in Long.MIN_VALUE..40 -> TODO("Hypothermia").takeUnless{prevHour.temp in Long.MIN_VALUE..40}
            in 90..Long.MAX_VALUE -> TODO("Heatstroke").takeUnless{prevHour.temp in 90..Long.MAX_VALUE}
            else -> null
        }
}