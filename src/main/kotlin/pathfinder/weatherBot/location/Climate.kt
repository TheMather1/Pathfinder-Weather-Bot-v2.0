package pathfinder.weatherBot.location

import pathfinder.diceSyntax.components.DiceComponent
import pathfinder.diceSyntax.components.DiceNumber
import pathfinder.diceSyntax.d
import pathfinder.diceSyntax.plus
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.weather.TemperatureWave
import java.time.LocalDate

enum class Climate(
    val winterTemp: Long, val springTemp: Long, val summerTemp: Long, val fallTemp: Long, val adjustPrecip: Int
) {
    COLD(
        20, 30, 40, 30, -1
    ) {
        override fun tempWave(start: LocalDate) = when ((1 d 100).toLong()) {
            in 1..20 -> tempWave(start, 1 d 4) { -3 d 10 }
            in 21..40 -> tempWave(start, (1 d 6) + 1) { -2 d 10 }
            in 41..60 -> tempWave(start, (1 d 6) + 2) { -1 d 10 }
            in 61..80 -> tempWave(start, (1 d 6) + 2) { DiceNumber(0L) }
            in 81..95 -> tempWave(start, (1 d 6) + 1) { 1 d 10 }
            in 96..99 -> tempWave(start, 1 d 4) { 2 d 10 }
            100L -> tempWave(start, 1 d 2) { 3 d 10 }
            else -> throw dHundredException
        }
    },
    TEMPERATE(
        30, 60, 80, 60, 0
    ) {
        override fun tempWave(start: LocalDate) = when ((1 d 100).toLong()) {
            in 1..5 -> tempWave(start, 1 d 2) { -3 d 10 }
            in 6..15 -> tempWave(start, 1 d 4) { -2 d 10 }
            in 16..35 -> tempWave(start, (1 d 4) + 1) { -1 d 10 }
            in 36..65 -> tempWave(start, (1 d 6) + 1) { DiceNumber(0L) }
            in 66..85 -> tempWave(start, (1 d 4) + 1) { 1 d 10 }
            in 86..95 -> tempWave(start, 1 d 4) { 2 d 10 }
            in 96..100 -> tempWave(start, 1 d 2) { 3 d 10 }
            else -> throw dHundredException
        }
    },
    TROPICAL(
        50, 75, 95, 75, 1
    ) {
        override fun tempWave(start: LocalDate) = when ((1 d 100).toLong()) {
            in 1..10 -> tempWave(start, 1 d 2) { -2 d 10 }
            in 11..25 -> tempWave(start, 1 d 2) { -1 d 10 }
            in 26..55 -> tempWave(start, 1 d 4) { DiceNumber(0L) }
            in 56..85 -> tempWave(start, 1 d 4) { 1 d 10 }
            in 86..100 -> tempWave(start, 1 d 2) { 2 d 10 }
            else -> throw dHundredException
        }
    };

    abstract fun tempWave(start: LocalDate): TemperatureWave
    protected fun tempWave(start: LocalDate, days: Number, function: () -> DiceComponent<*,*,*>) =
        TemperatureWave(start.plusDays(days.toLong()), function())

    fun text() = name.toString()
        .replace('_', ' ').lowercase().replaceFirstChar(Char::uppercase)
}