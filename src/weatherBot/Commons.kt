package weatherBot

import kotlin.math.absoluteValue
import kotlin.random.Random

infix fun Int.d(u: Int): Int{
    return (0..this.absoluteValue)
        .map { Random.nextInt(1, u) }.sum()
        .let {
            if (this < 0) it.inv()
            else it
        }
}