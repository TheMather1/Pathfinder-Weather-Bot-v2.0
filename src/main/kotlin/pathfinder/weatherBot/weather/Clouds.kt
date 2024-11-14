package pathfinder.weatherBot.weather

import pathfinder.diceSyntax.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.time.Season
import java.time.LocalDateTime

enum class Clouds(val description: String) {
    NONE("The sky is completely clear.") {
        override fun describeChange(prev: Clouds?) = when (prev) {
            null -> description
            NONE -> null
            LIGHT -> "The clouds dissipate. $description"
            MEDIUM -> "The clouds break up and dissipate. $description"
            OVERCAST -> "The overcast breaks apart and the clouds clear out. $description"
        }

        override fun iconUrl(time: LocalDateTime) = when(time.hour) {
            in 3..5 -> "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/moonset.png?raw=true"
            in 6..9 -> "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/sunrise.png?raw=true"
            in 10..16 -> "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/clear-day.png?raw=true"
            in 17..20 -> "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/sunset.png?raw=true"
            in 21..23 -> "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/moonrise.png?raw=true"
            else -> "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/clear-night.png?raw=true"
        }
    },
    LIGHT("The sky is dotted with a few small clouds.") {
        override fun describeChange(prev: Clouds?) = when (prev) {
            null -> description
            NONE -> "Some minor clouds start forming."
            LIGHT -> null
            MEDIUM -> "The clouds thin out until only a few small ones remain."
            OVERCAST -> "The overcast breaks up and thins out until only a few small clouds remain."
        }

        override fun iconUrl(time: LocalDateTime) = if(time.hour in 6..18) "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/partly-cloudy-day.png?raw=true"
        else "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/partly-cloudy-night.png?raw=true"
    },
    MEDIUM("The sky is crowded with large clouds.") {
        override fun describeChange(prev: Clouds?) = when (prev) {
            null -> description
            NONE -> "Large, heavy clouds roll in from the horizon."
            LIGHT -> "The clouds condense until they crowd the skies."
            MEDIUM -> null
            OVERCAST -> "The overcast breaks up into large clouds and the skies peek out through the gaps."
        }

        override fun iconUrl(time: LocalDateTime) = if(time.hour in 6..18) "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/overcast-day.png?raw=true"
        else "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/overcast-night.png?raw=true"
    },
    OVERCAST("The sky is entirely overcast.") {
        override fun describeChange(prev: Clouds?) = when (prev) {
            null -> description
            NONE -> "A heavy blanket of clouds rolls in from the horizon. $description"
            LIGHT -> "A heavy blanket of clouds rolls in from the horizon. $description"
            MEDIUM -> "The clouds merge into a heavy blanket. $description"
            OVERCAST -> null
        }

        override fun iconUrl(time: LocalDateTime) = "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/extreme.png?raw=true"
    };

    companion object {
        operator fun invoke() = when ((1 d 100).toInt()) {
            in 1..50 -> NONE
            in 51..70 -> LIGHT
            in 71..85 -> MEDIUM
            in 86..100 -> OVERCAST
            else -> throw dHundredException
        }
    }

    abstract fun describeChange(prev: Clouds?): String?
    abstract fun iconUrl(time: LocalDateTime): String

    fun adjustTemp(season: Season): Long =
        if (this == OVERCAST && (season == Season.SPRING || season == Season.SUMMER)) -10 else 10

    operator fun plus(i: Int): Clouds = entries[(ordinal + i).coerceAtMost(entries.size - 1)]
    override fun toString() = this.name.lowercase().replaceFirstChar { it.uppercaseChar() }
}
