package pathfinder.weatherBot.weather

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException

enum class Wind {
    LIGHT {
        override fun print(prevWind: Wind) = when {
            prevWind > this -> "The wind slows down to a gentle breeze."
            prevWind < this -> "The winds pick up gently."
            else -> null
        }
    },
    MODERATE {
        override fun print(prevWind: Wind) = when {
            prevWind > this -> "The forceful gusts have calmed to a moderate breeze."
            prevWind < this -> "The winds pick up considerably, may the wind be ever at your back!"
            else -> null
        }
    },
    STRONG {
        override fun print(prevWind: Wind) = when {
            prevWind > this -> "We seem to have gotten through the worst of it, the winds slow to a more bearable speed. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
            prevWind < this -> "Large gusts of wind begin to buffet across the area, kicking up dust. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
            else -> "The wind holds steady. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
        }
    },
    SEVERE {
        override fun print(prevWind: Wind) = when {
            prevWind > this -> "The winds have gradually calmed, going out in this weather is still dangerous. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
            prevWind < this -> "A dangerous gale flies across the area, kicking up debris. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
            else -> "The gale keeps buffeting. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
        }
    },
    WINDSTORM {
        override fun print(prevWind: Wind) = when {
            prevWind > this -> "If you get this message, either we survived apocalyptic-levels of wind, or the bot bugged out."
            prevWind < this -> "A windstorm kicks up, threatening to blow away anyone wandering outside! (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind))"
            else -> "The storm keeps raging. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
        }
    };

    companion object {
        operator fun invoke() = when (1 d 100) {
            in 1..50 -> LIGHT
            in 51..80 -> MODERATE
            in 81..90 -> STRONG
            in 91..95 -> SEVERE
            in 96..100 -> WINDSTORM
            else -> throw dHundredException
        }
    }

    abstract fun print(prevWind: Wind): String?
    operator fun plus(i: Int) = values()[(ordinal + i).coerceAtMost(values().size - 1)]
    operator fun minus(i: Int) = values()[(ordinal - i).coerceAtLeast(0)]
}