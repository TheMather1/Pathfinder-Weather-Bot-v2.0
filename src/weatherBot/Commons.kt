package weatherBot

import kotlin.math.absoluteValue
import kotlin.random.Random

infix fun Int.d(u: Int): Long {
    return (0..this.absoluteValue)
        .map { Random.nextLong(1, u.toLong()) }.sum()
        .let {
            if (this < 0) it.inv()
            else it
        }
}