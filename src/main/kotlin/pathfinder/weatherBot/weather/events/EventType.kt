package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Weather

interface EventType<T : EventType<T>> {
    val name: String
        get() = this::class.simpleName!!

    val description: String?
        get() = "TODO"

    fun finished(): String

    fun describeChange(prev: List<Event>): String?

    fun progress(hour: Hour, weather: Weather, event: Event): Event?

    val warn: String?
        get() = null

    companion object {
        operator fun invoke(hour: Hour, prevEvents: List<Event>, weather: Weather): List<Event> {
            return prevEvents.mapNotNull { it.progress(hour, weather) }.toMutableList().apply {
                if (none { it.type is Wildfire }) Wildfire.invoke(hour)?.let { add(it) }
            }
        }

        fun <T: EventType<T>> Event.progress(hour: Hour, weather: Weather): Event? {
            return type.progress(hour, weather, this)
        }
    }
}
