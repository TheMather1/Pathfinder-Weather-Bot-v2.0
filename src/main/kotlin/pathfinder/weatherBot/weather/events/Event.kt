package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.weather.Described
import pathfinder.weatherBot.weather.Weather

interface Event<in T>: Described<T> {
    companion object {
        operator fun invoke(prevEvents: List<Event<*>>, weather: Weather): List<Event<*>> {
            return prevEvents.mapNotNull { it.progress(weather) }.toMutableList().apply {
                if (none { it is Wildfire }) Wildfire.invoke(weather)?.let {add(it)}
            }
        }
    }

    fun progress(weather: Weather): Event<T>?
}