package pathfinder.weatherBot.weather.events.tornado

import pathfinder.diceSyntax.d
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.events.Event
import pathfinder.weatherBot.weather.events.EventType
import pathfinder.weatherBot.weather.precipitation.Snow
import pathfinder.weatherBot.weather.precipitation.Thunder

open class Tornado : EventType<Tornado> {

    companion object {
        operator fun invoke(hour: Hour) = if (hour.weather.precipitation.type.isThunder && Thunder.tornado(hour.weather.wind)) {
            val t = hour.time.plusHours((3 d 6).toLong())
            if (hour.weather.precipitation.type is Snow) Event(0, hour.time, t, Snownado())
            else Event(0, hour.time, t, Tornado())
        } else null
    }

    override fun progress(hour: Hour, weather: Weather, event: Event) = when {
        hour.time >= event.end -> null
        weather.precipitation.type is Snow -> Event(0, hour.time, event.end, Snownado()).also {event.end = hour.time }
        else -> event
    }

    override fun finished() = "The tornado peters out and disappears, leaving only debris behind."

    override fun describeChange(prev: List<Event>): String? = when (prev.firstOrNull { it.type is Tornado }?.type) {
        null -> "Thrashing winds from opposing directions form into a tornado."
        is Snownado -> ""
        is Firenado -> ""
        else -> "The tornado keeps rampaging across the landscape."
    }

}
