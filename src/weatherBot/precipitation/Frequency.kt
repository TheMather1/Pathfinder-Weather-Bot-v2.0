package weatherBot.precipitation

import java.lang.Math.max
import java.lang.Math.min

enum class Frequency(chance: Int){
    DROUGHT(5),
    RARE(15),
    INTERMITTENT(30),
    COMMON(60),
    CONSTANT(95);


    operator fun inc(): Frequency = plus(1)
    operator fun dec(): Frequency = minus(1)
    operator fun plus(i: Int): Frequency = values()[min(this.ordinal+i, 2)]
    operator fun minus(i: Int): Frequency = values()[max(this.ordinal - i, 0)]
}