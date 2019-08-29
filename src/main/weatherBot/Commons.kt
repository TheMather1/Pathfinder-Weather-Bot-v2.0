package weatherBot

import java.lang.RuntimeException
import kotlin.math.absoluteValue
import kotlin.random.Random

infix fun Int.d(u: Int): Long {
    return (0 until (this.absoluteValue))
        .map { Random.nextLong(1, u.toLong()) }.sum()
        .let {
            if (this < 0) it.inv()
            else it
        }
}

val dHundredException = RuntimeException("d% yielded a number not between 1 and 100.")