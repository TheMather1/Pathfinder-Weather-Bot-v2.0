package pathfinder.weatherBot.weather.precipitation

import kotlin.math.max
import kotlin.math.min

enum class Frequency(val chance: Long){
    DROUGHT(5),
    RARE(15),
    INTERMITTENT(30),
    COMMON(60),
    CONSTANT(95);


    operator fun inc(): Frequency = plus(1)
    operator fun dec(): Frequency = minus(1)
    operator fun plus(i: Int): Frequency = values()[min(ordinal + i, values().lastIndex)]
    operator fun minus(i: Int): Frequency = values()[max(ordinal - i, 0)]
}