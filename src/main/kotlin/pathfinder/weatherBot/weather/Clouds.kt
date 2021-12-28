package pathfinder.weatherBot.weather

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.time.Season

enum class Clouds(val description: String) {
    NONE("The sky is completely clear.") {
        override fun print(clouds: Clouds) = if (clouds > this) "The clouds have cleared up! $description" else null
    },
    LIGHT("There are a few small clouds.") {
        override fun print(clouds: Clouds) = when (clouds.compareTo(this)) {
            -1 -> "Some minor clouds are rolling in."
            1 -> "The sky has cleared up somewhat. $description"
            else -> null
        }
    },
    MEDIUM("The sky is dotted with large clouds.") {
        override fun print(clouds: Clouds) = when (clouds.compareTo(this)) {
            -1 -> "Heavy clouds line the skies."
            1 -> "The overcast disperses and the skies peek out."
            else -> null
        }
    },
    OVERCAST("It is entirely overcast.") {
        override fun print(clouds: Clouds) = if (clouds < this) "The sky is obscured by heavy, dark clouds." else null
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

    abstract fun print(clouds: Clouds): String?

    fun adjustTemp(season: Season): Long =
        if (this == OVERCAST && (season == Season.SPRING || season == Season.SUMMER)) -10 else 10

    operator fun plus(i: Int): Clouds = values()[(ordinal + i).coerceAtMost(values().size - 1)]
}