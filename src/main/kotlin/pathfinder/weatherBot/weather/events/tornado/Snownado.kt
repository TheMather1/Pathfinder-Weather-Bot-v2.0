package pathfinder.weatherBot.weather.events.tornado

import pathfinder.weatherBot.weather.events.Event
import java.time.LocalDateTime

class Snownado(start: LocalDateTime, end: LocalDateTime) : Tornado(start, end) {
    override val finished = "The snownado dissipates, leaving behind snow littered with debris."

    override fun describeChange(prev: List<Event<*>>) = when(prev.firstOrNull { it is Tornado }) {
        null -> "Thrashing winds and falling snow combine into a terrifying tornado."
        is Snownado -> "The snownado keeps rampaging across the landscape."
        is Firenado -> ""
        else -> "The tornado sucks up the falling snow, becoming a snownado."
    }
}