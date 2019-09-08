package pathfinder.weatherBot.time

import java.time.LocalDateTime

class Hour(private val calendar: Calendar, private val now: LocalDateTime) {
    val weather = calendar.days[now.toLocalDate().compareTo(calendar.days[1].day) + 1].weather
    val precipitation = weather.precipitation(now.toLocalTime())
    val clouds = weather.clouds(now.toLocalTime())
    val wind = weather.wind(now.toLocalTime())
    val temp = weather.temp(now.toLocalTime())

    val execute: String
        get() = print.also { precipitation?.fall() }

    val print: String
        get(){
            val precipitationDescription = precipitation?.print(prevHour.precipitation) ?: prevHour.precipitation?.finished()
            val cloudDescription = clouds.print(prevHour.clouds)
            val windDescription = wind.print(prevHour.wind)
            TODO("Description formatting.")
        }

    private val prevHour: Hour
            get() = Hour(calendar, now.minusHours(1))

    private val extremeTempDescription: String?
        get() = when(temp + (precipitation?.tempAdjust ?: 0)){
            in Long.MIN_VALUE..40 -> TODO("Hypothermia").takeUnless{prevHour.temp in Long.MIN_VALUE..40}
            in 90..Long.MAX_VALUE -> TODO("Heatstroke").takeUnless{prevHour.temp in 90..Long.MAX_VALUE}
            else -> null
        }
}