package pathfinder.weatherBot

import java.io.File
import java.lang.RuntimeException
import java.util.*
import kotlin.math.absoluteValue
import kotlin.random.Random


val CONFIG_FILE = File("location.properties").apply { if (!exists()) createNewFile() }
var config = Properties()

infix fun Int.d(u: Int): Long {
    return (0 until (this.absoluteValue))
        .map { Random.nextLong(1, u.toLong()) }.sum()
        .let {
            if (this < 0) it.inv()
            else it
        }
}

val dHundredException = RuntimeException("d% yielded a number not between 1 and 100.")