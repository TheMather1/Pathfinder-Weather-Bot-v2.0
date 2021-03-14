package pathfinder.weatherBot.weather

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException

enum class Wind {
    LIGHT {
        override fun print(prevWind: Wind) = when {
            prevWind > this -> "The wind slows down to a gentle breeze."
            prevWind < this -> "The winds pick up gently."
            else -> "The wind holds steady."
        }
    },
    MODERATE {
        override fun print(prevWind: Wind) = when {
            prevWind > this -> "The forceful gusts have calmed to a moderate breeze."
            prevWind < this -> "The winds pick up considerably, may the wind be ever at your back!"
            else -> "The wind holds steady."
        }
    },
    STRONG {
        override fun print(prevWind: Wind) = when {
            prevWind > this -> "We seem to have gotten through the worst of it, the winds slow to a more bearable speed. Still, be careful out there! (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
            prevWind < this -> "Large gusts of wind begin to buffet across the area, blowing bits of litter and gravel across the ground. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
            else -> "The wind holds steady. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
        }
    },
    SEVERE {
        override fun print(prevWind: Wind) = when {
            prevWind > this -> "The winds have gradually calmed, going out in this weather is still dangerous. Stay safe! (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
            prevWind < this -> "A dangerous gale flies across the area. Tiny objects and creatures will be sent flying! Watch out, and stay safe! (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
            else -> "The wind holds forcefully steady. (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
        }
    },
    WINDSTORM {
        override fun print(prevWind: Wind) = when {
            prevWind > this -> "if you get this message, either we survived apocalyptic-levels of wind, or the bot bugged out."
            prevWind < this -> "A windstorm kicks up! Take shelter and keep away from windows! If you must be out in the streets, watch out for flying kobolds! (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind))"
            else -> "It's not stopping!? When will it end? (Incurs penalties based on the wind strength chart https://www.d20pfsrd.com/Gamemastering/environment/weather/#Wind)"
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

    abstract fun print(prevWind: Wind): String
    operator fun plus(i: Int) = values()[(ordinal + i).coerceAtMost(values().size - 1)]
    operator fun minus(i: Int) = values()[(ordinal - i).coerceAtLeast(0)]
}