package weatherBot.precipitation

import kotlin.math.max
import kotlin.math.min

enum class Intensity(val chance: Long){
    LIGHT(5),
    MEDIUM(15),
    HEAVY(30);

    operator fun inc(): Intensity = plus(1)
    operator fun dec(): Intensity = minus(1)
    operator fun plus(i: Int): Intensity = values()[min(ordinal+i, values().lastIndex)]
    operator fun minus(i: Int): Intensity = values()[max(ordinal-i, 0)]
}