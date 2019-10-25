package pathfinder.weatherBot.weather

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.time.Season

enum class Clouds(val description: String) {
    NONE("The sky is completely clear.") {
        override fun print(clouds: Clouds) = if (clouds > this) TODO() else ""
    },
    LIGHT("There are a few small clouds.") {
        override fun print(clouds: Clouds) = when (clouds.compareTo(this)) {
            -1 -> TODO()
            1 -> TODO()
            else -> ""
        }
    },
    MEDIUM("The sky is dotted with large clouds.") {
        override fun print(clouds: Clouds) = when (clouds.compareTo(this)) {
            -1 -> TODO()
            1 -> TODO()
            else -> ""
        }
    },
    OVERCAST("It is entirely overcast.") {
        override fun print(clouds: Clouds) = if (clouds < this) TODO() else ""
    };

    companion object {
        operator fun invoke() = when (1 d 100) {
            in 1..50 -> NONE
            in 51..70 -> LIGHT
            in 71..85 -> MEDIUM
            in 86..100 -> OVERCAST
            else -> throw dHundredException
        }
    }

    abstract fun print(clouds: Clouds): String

    fun adjustTemp(season: Season): Long = if (this == OVERCAST && (season == Season.SPRING || season == Season.SUMMER)) -10 else 10
    operator fun plus(i: Int): Clouds = values()[(ordinal + i).coerceAtMost(values().size - 1)]
}