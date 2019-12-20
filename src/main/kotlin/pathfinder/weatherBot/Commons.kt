package pathfinder.weatherBot

import kotlin.math.absoluteValue
import kotlin.random.Random

infix fun Int.d(u: Int) = (0 until (this.absoluteValue))
        .map { Random.nextLong(1L, u.toLong()) }.sum()
        .let {
            if (this < 0) it.inv()
            else it
        }

fun String.removeSuffixOrNull(suffix: CharSequence, ignoreCase: Boolean = false) = takeIf { startsWith(suffix, ignoreCase) }?.drop(suffix.length)

val dHundredException = RuntimeException("d% yielded a number not between 1 and 100.")