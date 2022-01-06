package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Weather
import java.time.LocalDateTime

interface Event<T: Event<T>> {
    val start: LocalDateTime
    val end: LocalDateTime

    companion object {
        operator fun invoke(hour: Hour, prevEvents: List<Event<*>>, weather: Weather): List<Event<*>> {
            return prevEvents.mapNotNull { it.progress(hour, weather) }.toMutableList().apply {
                if (none { it is Wildfire }) Wildfire.invoke(hour)?.let { add(it) }
            }
        }
    }

    fun description(prev: List<Event<*>>): String?
    val finished: String

    fun progress(hour: Hour, weather: Weather): Event<T>?
}