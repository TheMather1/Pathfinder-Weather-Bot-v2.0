package weatherBot.weather

import weatherBot.d
import weatherBot.dHundredException
import weatherBot.time.Season

enum class Clouds(val description: String) {
    NONE("The sky is completely clear."),
    LIGHT("There are a few small clouds."),
    MEDIUM("The sky is dotted with large clouds."),
    OVERCAST("It is entirely overcast.");
    companion object{
        operator fun invoke() = when (1 d 100){
            in 1..50 -> NONE
            in 51..70 -> LIGHT
            in 71..85 -> MEDIUM
            in 86..100 -> OVERCAST
            else -> throw dHundredException
        }
    }

    fun adjustTemp(season: Season): Long = if (this == OVERCAST && (season == Season.SPRING || season == Season.SUMMER)) -10 else 10
    operator fun plus(i: Int): Clouds = values()[(ordinal + i).coerceAtMost(values().size - 1)]
}