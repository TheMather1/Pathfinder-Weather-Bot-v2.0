package pathfinder.weatherBot.weather

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.time.Season

enum class Clouds(protected val description: String) {
    NONE("The sky is completely clear.") {
        override fun print(clouds: Clouds?) = when(clouds) {
            null -> description
            NONE -> null
            LIGHT -> "The clouds clear up. $description"
            else -> LIGHT.print(clouds)
        }
    },
    LIGHT("The sky is dotted with a few small clouds.") {
        override fun print(clouds: Clouds?) = when(clouds) {
            null -> description
            NONE -> "Some minor clouds are rolling in."
            LIGHT -> null
            MEDIUM -> "The sky has cleared up somewhat. $description"
            OVERCAST -> MEDIUM.print(clouds)
        }
    },
    MEDIUM("The sky is crowded with large clouds.") {
        override fun print(clouds: Clouds?) = when(clouds) {
            null -> description
            NONE -> LIGHT.print(clouds)
            LIGHT -> "Heavy clouds start to cover the skies."
            MEDIUM -> null
            OVERCAST -> "The overcast breaks up and the skies peek out through the gaps."
        }
    },
    OVERCAST("The sky is entirely overcast.") {
        override fun print(clouds: Clouds?) = when(clouds) {
            null -> description
            MEDIUM -> "The sky is obscured by heavy, dark clouds."
            OVERCAST -> null
            else -> MEDIUM.print(clouds)
        }
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

    abstract fun print(clouds: Clouds?): String?

    fun adjustTemp(season: Season): Long =
        if (this == OVERCAST && (season == Season.SPRING || season == Season.SUMMER)) -10 else 10

    operator fun plus(i: Int): Clouds = values()[(ordinal + i).coerceAtMost(values().size - 1)]
}