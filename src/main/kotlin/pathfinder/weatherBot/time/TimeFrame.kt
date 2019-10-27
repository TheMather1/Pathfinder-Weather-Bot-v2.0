package pathfinder.weatherBot.time

import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime

class TimeFrame(val start: LocalDateTime, val end: LocalDateTime) {
    companion object {
        val empty = TimeFrame()
    }

    private constructor() : this(LocalDateTime.MIN, LocalDateTime.MIN)

    val duration
        get() = Duration.between(start, end)

    operator fun contains(temporal: LocalDateTime?) = temporal?.let { it > start && it < end } ?: false
    operator fun contains(temporal: LocalTime?) = temporal?.let { it > start.toLocalTime() && it < end.toLocalTime() }
            ?: false
}