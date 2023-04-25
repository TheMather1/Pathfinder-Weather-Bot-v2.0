package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Weather
import java.io.Serializable
import java.time.LocalDateTime

abstract class Event<T : Event<T>>(
    val start: LocalDateTime, var end: LocalDateTime
) : Serializable {
    abstract val finished: String
    var active = false
    val name: String
        get() = this::class.simpleName!!

    companion object {
        operator fun invoke(hour: Hour, prevEvents: List<Event<*>>, weather: Weather): List<Event<*>> {
            return prevEvents.mapNotNull { it.progress(hour, weather) }.toMutableList().apply {
                if (none { it is Wildfire }) Wildfire.invoke(hour)?.let { add(it) }
            }
        }
    }

    abstract fun describeChange(prev: List<Event<*>>): String?

    abstract fun progress(hour: Hour, weather: Weather): Event<T>?
}
