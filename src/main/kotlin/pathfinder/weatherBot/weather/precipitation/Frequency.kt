package pathfinder.weatherBot.weather.precipitation

import kotlin.math.max
import kotlin.math.min

enum class Frequency(val chance: Long) {
enum class Frequency(val chance: Long) {
    DROUGHT(5),
    RARE(15),
    INTERMITTENT(30),
    COMMON(60),
    CONSTANT(95);


    operator fun inc() = plus(1)
    operator fun dec() = minus(1)
    operator fun plus(i: Int) = values()[min(ordinal + i, values().lastIndex)]
    operator fun minus(i: Int) = values()[max(ordinal - i, 0)]
}