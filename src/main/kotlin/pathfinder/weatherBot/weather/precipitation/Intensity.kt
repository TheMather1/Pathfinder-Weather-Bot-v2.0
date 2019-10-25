package pathfinder.weatherBot.weather.precipitation

import kotlin.math.max
import kotlin.math.min

enum class Intensity {
    LIGHT,
    MEDIUM,
    HEAVY;

    operator fun inc() = plus(1)
    operator fun dec() = minus(1)
    operator fun plus(i: Int) = values()[min(ordinal + i, values().lastIndex)]
    operator fun minus(i: Int) = values()[max(ordinal - i, 0)]
}