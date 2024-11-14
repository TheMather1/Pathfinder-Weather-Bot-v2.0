package pathfinder.weatherBot.weather

import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import pathfinder.diceSyntax.d
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.location.Climate
import pathfinder.weatherBot.time.Season
import java.time.LocalDate
import java.time.LocalTime
import java.time.LocalTime.NOON
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToLong

@Embeddable
class TemperatureRange(config: GuildConfig, date: LocalDate, season: Season, climate: Climate, oldTemp: TemperatureRange? = null) {
    @Embedded
    private val temperatureWave: TemperatureWave = oldTemp?.temperatureWave?.progress(date, config.climate) ?: config.climate.tempWave(date)
    private val highTemp = season.temp(config) + temperatureWave()
    private val lowTemp = highTemp - (2 d 6).toLong() - 3
    private val tempPrev = oldTemp?.lowTemp ?: lowTemp

    fun tempAtHour(time: LocalTime): Temperature {
        val low = if (time.isBefore(NOON)) tempPrev else lowTemp
        val halfDif = (highTemp - low) / 2
        val cosine = cos((time.hour.toFloat() - 12) / 12 * PI)
        return Temperature(low + halfDif + (cosine * halfDif).roundToLong())
    }
}
