package pathfinder.weatherBot.weather

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException

enum class Wind {
    LIGHT {
        override fun print(prevWind: Wind): String = when{
            prevWind > this -> TODO()
            prevWind < this -> TODO()
            else -> ""
        }
    },
    MODERATE {
        override fun print(prevWind: Wind): String = when{
            prevWind > this -> TODO()
            prevWind < this -> TODO()
            else -> ""
        }
    },
    STRONG {
        override fun print(prevWind: Wind): String = when{
            prevWind > this -> TODO()
            prevWind < this -> TODO()
            else -> ""
        }
    },
    SEVERE {
        override fun print(prevWind: Wind): String = when{
            prevWind > this -> TODO()
            prevWind < this -> TODO()
            else -> ""
        }
    },
    WINDSTORM {
        override fun print(prevWind: Wind): String = when{
            prevWind > this -> TODO()
            prevWind < this -> TODO()
            else -> ""
        }
    };
    companion object{
        operator fun invoke(): Wind = when(1 d 100){
            in 1..50 -> LIGHT
            in 51..80 -> MODERATE
            in 81..90 -> STRONG
            in 91..95 -> SEVERE
            in 96..100 -> WINDSTORM
            else -> throw dHundredException
        }
    }

    abstract fun print(prevWind: Wind): String
    operator fun plus(i: Int): Wind = values()[(ordinal + i).coerceAtMost(values().size - 1)]
    operator fun minus(i: Int): Wind = values()[(ordinal - i).coerceAtLeast(0)]
}