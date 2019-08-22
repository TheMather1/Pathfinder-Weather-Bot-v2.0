package weatherBot

import weatherBot.time.Season
import java.lang.RuntimeException

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
            else -> throw RuntimeException("d% yielded a number not between 1 and 100.")
        }
    }

    fun adjustTemp(season: Season): Int = if (season == Season.SPRING || season == Season.SUMMER) -10 else 10
}