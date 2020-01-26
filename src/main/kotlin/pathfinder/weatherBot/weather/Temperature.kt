package pathfinder.weatherBot.weather

import pathfinder.weatherBot.d
import pathfinder.weatherBot.time.Day
import java.time.LocalTime
import java.time.LocalTime.NOON
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToLong

class Temperature(val day: Day, oldTemp: Temperature? = null) {
    val tempVar: TempVar = oldTemp?.tempVar?.next() ?: day.forecast.biome.climate.tempVar()
    val highTemp = day.season.temp(day.forecast.biome) + tempVar()
    val lowTemp = highTemp - (2 d 6) - 3
    val tempPrev = oldTemp?.lowTemp ?: lowTemp

    fun tempAtHour(time: LocalTime): Long {
        val low = if(time < NOON) tempPrev else lowTemp
        val halfDif = (highTemp - low)/2
        return low + halfDif + cos((time.hour.toFloat()-12) / 12 * PI).roundToLong() * halfDif
    }
}