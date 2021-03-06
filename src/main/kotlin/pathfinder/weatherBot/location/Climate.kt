package pathfinder.weatherBot.location

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException

enum class Climate(val winterTemp: Long, val springTemp: Long, val summerTemp: Long, val fallTemp: Long, val adjustPrecip: Int) {
    COLD(
            20,
            30,
            40,
            30,
            -1
    ) {
        override fun tempVariation() = when (1 d 100) {
            in 1..20 -> ({ -3 d 10 } to (1 d 4))
            in 21..40 -> ({ -2 d 10 } to (1 d 6) + 1)
            in 41..60 -> ({ -1 d 10 } to (1 d 6) + 2)
            in 61..80 -> ({ 0L } to (1 d 6) + 2)
            in 81..95 -> ({ 1 d 10 } to (1 d 6) + 1)
            in 96..99 -> ({ 2 d 10 } to (1 d 4))
            100L -> ({ 3 d 10 } to (1 d 2))
            else -> throw dHundredException
        }
    },
    TEMPERATE(
            30,
            60,
            80,
            60,
            0
    ) {
        override fun tempVariation() = when (1 d 100) {
            in 1..5 -> ({ -3 d 10 } to (1 d 2))
            in 6..15 -> ({ -2 d 10 } to (1 d 4))
            in 16..35 -> ({ -1 d 10 } to (1 d 4) + 1)
            in 36..65 -> ({ 0L } to (1 d 6) + 1)
            in 66..85 -> ({ 1 d 10 } to (1 d 4) + 1)
            in 86..95 -> ({ 2 d 10 } to (1 d 4))
            in 96..100 -> ({ 3 d 10 } to (1 d 2))
            else -> throw dHundredException
        }
    },
    TROPICAL(
            50,
            75,
            95,
            75,
            1
    ) {
        override fun tempVariation() = when (1 d 100) {
            in 1..10 -> ({ -2 d 10 } to (1 d 2))
            in 11..25 -> ({ -1 d 10 } to (1 d 2))
            in 26..55 -> ({ 0L } to (1 d 4))
            in 56..85 -> ({ 1 d 10 } to (1 d 4))
            in 86..100 -> ({ 2 d 10 } to (1 d 2))
            else -> throw dHundredException
        }
    };

    abstract fun tempVariation(): Pair<() -> Long, Long>
}