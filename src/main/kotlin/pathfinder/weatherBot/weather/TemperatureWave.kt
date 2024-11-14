package pathfinder.weatherBot.weather

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import pathfinder.diceSyntax.components.DiceComponent
import pathfinder.weatherBot.location.Climate
import java.time.LocalDate

@Embeddable
data class TemperatureWave(
    @Column(name = "TEMP_WAVE_END")
    private val end: LocalDate,
    @Column(name = "TEMP_WAVE_FUN")
    private val diceFun: DiceComponent<*,*,*>
) {

    operator fun invoke() = diceFun.toLong()

    fun progress(date: LocalDate, climate: Climate) = if (date < end) this else climate.tempWave(date)
}