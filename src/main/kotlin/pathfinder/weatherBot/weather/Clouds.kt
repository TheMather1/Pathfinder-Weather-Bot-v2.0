package pathfinder.weatherBot.weather

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.time.Season

enum class Clouds(protected val description: String) {
    NONE("The sky is completely clear.") {
        override fun print(prev: Clouds?) = when(prev) {
            null -> description
            NONE -> null
            LIGHT -> "The clouds dissipate. $description"
            MEDIUM -> "The clouds break up and dissipate. $description"
            OVERCAST -> "The overcast breaks apart and the clouds clear out. $description"
        }
    },
    LIGHT("The sky is dotted with a few small clouds.") {
        override fun print(prev: Clouds?) = when(prev) {
            null -> description
            NONE -> "Some minor clouds forming."
            LIGHT -> null
            MEDIUM -> "The clouds thin out until only a few small ones remain."
            OVERCAST -> "The overcast breaks up and thins out until only a few small clouds remain."
        }
    },
    MEDIUM("The sky is crowded with large clouds.") {
        override fun print(prev: Clouds?) = when(prev) {
            null -> description
            NONE -> "Large, heavy clouds roll in from the horizon."
            LIGHT -> "The clouds condense until they crowd the skies."
            MEDIUM -> null
            OVERCAST -> "The overcast breaks up into large clouds and the skies peek out through the gaps."
        }
    },
    OVERCAST("The sky is entirely overcast.") {
        override fun print(prev: Clouds?) = when(prev) {
            null -> description
            NONE -> "A heavy blanket of clouds rolls in from the horizon. $description"
            LIGHT -> "A heavy blanket of clouds rolls in from the horizon. $description"
            MEDIUM -> "The clouds merge into a heavy blanket. $description"
            OVERCAST -> null
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

    abstract fun print(prev: Clouds?): String?

    fun adjustTemp(season: Season): Long =
        if (this == OVERCAST && (season == Season.SPRING || season == Season.SUMMER)) -10 else 10

    operator fun plus(i: Int): Clouds = values()[(ordinal + i).coerceAtMost(values().size - 1)]
}