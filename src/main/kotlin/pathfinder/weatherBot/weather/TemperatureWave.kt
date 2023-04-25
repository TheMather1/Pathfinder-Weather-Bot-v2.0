package pathfinder.weatherBot.weather

import pathfinder.weatherBot.location.Climate
import java.io.Serializable
import java.time.LocalDate

data class TemperatureWave(
    private val climate: Climate, private val end: LocalDate, private val diceFun: () -> Long
) : Serializable {

    operator fun invoke() = diceFun()

    fun progress(date: LocalDate) = if (date < end) this else climate.tempWave(date)
}