package pathfinder.weatherBot.weather

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException

enum class Wind {
    LIGHT {
        override fun print(prevWind: Wind?) = when {
            prevWind == null -> "The air is perfectly still save the occasional breeze."
            prevWind > this -> "The wind slows to a near standstill."
            prevWind < this -> "Either you survived total vacuum, or the bot bugged out."
            else -> null
        }
    },
    MODERATE {
        override fun print(prevWind: Wind?) = when {
            prevWind == null -> "There is a calm wind."
            prevWind > this -> "The forceful gusts have calmed to a stable wind."
            prevWind < this -> "A calm wind picks up."
            else -> null
        }
    },
    STRONG {
        override fun print(prevWind: Wind?) = when {
            prevWind == null -> "Gusts of strong wind buffet the area. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
            prevWind > this -> "The wind slows to more bearable gusts. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
            prevWind < this -> "Large gusts of wind begin to buffet across the area, kicking up dust. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
            else -> null
        }
    },
    SEVERE {
        override fun print(prevWind: Wind?) = when {
            prevWind == null -> "An intense gale buffets the area. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
            prevWind > this -> "The windstorm gradually calms to a gale. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
            prevWind < this -> "An intense gale gale flies across the area, kicking up debris. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
            else -> null
        }
    },
    WINDSTORM {
        override fun print(prevWind: Wind?) = when {
            prevWind == null -> "A dangerous windstorm rages across the area. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
            prevWind > this -> "If you get this message, either we survived apocalyptic-levels of wind, or the bot bugged out."
            prevWind < this -> "A windstorm kicks up, threatening to blow away anyone wandering outside! (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind))"
            else -> null
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

    abstract fun print(prevWind: Wind?): String?
    operator fun plus(i: Int) = values()[(ordinal + i).coerceAtMost(values().size - 1)]
    operator fun minus(i: Int) = values()[(ordinal - i).coerceAtLeast(0)]
}