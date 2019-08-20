package weatherBot.precipitation

import java.lang.Math.max
import java.lang.Math.min

enum class Intensity(table: Int){
    LIGHT(0){
        val chance = 5
    },
    MEDIUM(1){
        val chance = 15
    },
    HEAVY(2){
        val chance = 30
    };

    operator fun inc(): Intensity = plus(1)
    operator fun dec(): Intensity = minus(1)
    operator fun plus(i: Int): Intensity = values()[min(this.ordinal+i, 2)]
    operator fun minus(i: Int): Intensity = values()[max(this.ordinal-i, 0)]
}