package pathfinder.weatherBot.weather

import pathfinder.weatherBot.d
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.time.Season
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime
import java.time.LocalTime.NOON
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToLong

class TemperatureRange(config: GuildConfig, date: LocalDate, season: Season, oldTemp: TemperatureRange? = null) :
    Serializable {
    private val temperatureWave: TemperatureWave = oldTemp?.temperatureWave?.next(date) ?: config.climate.tempWave(date)
    private val highTemp = season.temp(config) + temperatureWave()
    private val lowTemp = highTemp - (2 d 6) - 3
    private val tempPrev = oldTemp?.lowTemp ?: lowTemp

    fun tempAtHour(time: LocalTime): Temperature {
        val low = if (time.isBefore(NOON)) tempPrev else lowTemp
        val halfDif = (highTemp - low) / 2
        val cosine = cos((time.hour.toFloat() - 12) / 12 * PI)
        return Temperature(low + halfDif + (cosine * halfDif).roundToLong())
    }
}
