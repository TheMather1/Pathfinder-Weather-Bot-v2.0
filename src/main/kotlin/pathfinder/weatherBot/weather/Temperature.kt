package pathfinder.weatherBot.weather

import pathfinder.weatherBot.d
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.time.Season
import java.io.Serializable
import java.time.LocalTime
import java.time.LocalTime.NOON
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToLong

class Temperature(config: GuildConfig, season: Season, oldTemp: Temperature? = null): Serializable {
    val tempVar: TempVar = oldTemp?.tempVar?.next() ?: config.climate.tempVar()
    val highTemp = season.temp(config) + tempVar()
    val lowTemp = highTemp - (2 d 6) - 3
    val tempPrev = oldTemp?.lowTemp ?: lowTemp

    fun tempAtHour(time: LocalTime): Long {
        val low = if(time < NOON) tempPrev else lowTemp
        val halfDif = (highTemp - low)/2
        return low + halfDif + cos((time.hour.toFloat()-12) / 12 * PI).roundToLong() * halfDif
    }
}