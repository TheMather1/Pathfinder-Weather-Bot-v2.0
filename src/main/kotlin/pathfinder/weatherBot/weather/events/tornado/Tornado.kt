package pathfinder.weatherBot.weather.events.tornado

import pathfinder.weatherBot.d
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.events.Event
import pathfinder.weatherBot.weather.precipitation.Thunder
import pathfinder.weatherBot.weather.precipitation.snow.Snow
import java.time.LocalDateTime

open class Tornado(start: LocalDateTime, end: LocalDateTime) : Event<Tornado>(start, end) {

    companion object {
        operator fun invoke(hour: Hour): Tornado? {
            return if (hour.weather.precipitation is Thunder && Thunder.tornado(hour.weather.wind)) {
                val t = hour.time.plusHours(3 d 6)
                if (hour.weather.precipitation is Snow) Snownado(hour.time, t)
                else Tornado(hour.time, t)
            } else null
        }
    }

    override fun progress(hour: Hour, weather: Weather) = when {
        hour.time >= end -> null
        weather.precipitation is Snow -> Snownado(hour.time, end).also { end = hour.time }
        else -> this
    }

    override val finished = "The tornado peters out and disappears, leaving only debris behind."

    override fun describeChange(prev: List<Event<*>>): String? = when (prev.firstOrNull { it is Tornado }) {
        null -> "Thrashing winds from opposing directions form into a tornado."
        is Snownado -> ""
        is Firenado -> ""
        else -> "The tornado keeps rampaging across the landscape."
    }

}
