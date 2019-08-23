package weatherBot.time

import java.time.Duration
import java.time.LocalDateTime

class TimeFrame(val start: LocalDateTime, val end: LocalDateTime) {
    companion object{
        val empty = TimeFrame()
    }

    private constructor() : this(LocalDateTime.MIN, LocalDateTime.MIN)

    val duration: Duration
        get() = Duration.between(start, end)

    operator fun contains(temporal: LocalDateTime?): Boolean = temporal?.let { it > start && it < end } ?: false
}