package weatherBot.location

import weatherBot.d
import java.lang.RuntimeException

enum class Climate {
    COLD{
        val winterTemp = 20
        val springTemp = 30
        val summerTemp = 40
        val fallTemp = 30
        fun adjustPrecip( u: Int ):Int { return u-1 }
        fun variation(): Pair<Int, Int>{
            return when (1 d 100){
                in 1..20 ->  (-3 d 10) to (1 d 4)
                in 21..40 ->  (-2 d 10) to (1 d 6)+1
                in 41..60 ->  (-1 d 10) to (1 d 6)+2
                in 61..80 ->  0 to (1 d 6)+2
                in 81..95 ->  (1 d 10) to (1 d 6)+1
                in 96..99 ->  (2 d 10) to (1 d 4)
                100 -> (3 d 10) to (1 d 2)
                else -> throw RuntimeException("d% yielded a number not between 1 and 100.")
            }
        }
    },
    TEMPERATE{
        val winterTemp = 30
        val springTemp = 60
        val summerTemp = 80
        val fallTemp = 60
        fun adjustPrecip( u: Int ):Int { return u }
        fun variation(): Pair<Int, Int>{
            return when (1 d 100){
                in 1..5 ->  (-3 d 10) to (1 d 2)
                in 6..15 ->  (-2 d 10) to (1 d 4)
                in 16..35 ->  (-1 d 10) to (1 d 4)+1
                in 36..65 ->  0 to (1 d 6)+1
                in 66..85 ->  (1 d 10) to (1 d 4)+1
                in 86..95 ->  (2 d 10) to (1 d 4)
                in 96..100 -> (3 d 10) to (1 d 2)
                else -> throw RuntimeException("d% yielded a number not between 1 and 100.")
            }
        }
    },
    TROPICAL{
        val winterTemp = 50
        val springTemp = 75
        val summerTemp = 95
        val fallTemp = 75
        fun adjustPrecip( u: Int ):Int { return u+1 }
        fun variation(): Pair<Int, Int>{
            return when (1 d 100){
                in 1..10 ->  (-2 d 10) to (1 d 2)
                in 11..25 ->  (-1 d 10) to (1 d 2)
                in 26..55 ->  0 to (1 d 4)
                in 56..85 ->  (1 d 10) to (1 d 4)
                in 86..100 -> (2 d 10) to (1 d 2)
                else -> throw RuntimeException("d% yielded a number not between 1 and 100.")
            }
        }
    }
}