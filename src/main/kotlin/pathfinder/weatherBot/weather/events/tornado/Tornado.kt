package pathfinder.weatherBot.weather.events.tornado

import pathfinder.diceSyntax.d
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.events.Event
import pathfinder.weatherBot.weather.events.EventType
import pathfinder.weatherBot.weather.events.Wildfire
import pathfinder.weatherBot.weather.precipitation.Snow
import pathfinder.weatherBot.weather.precipitation.Thunder

open class Tornado : EventType {

    override fun finished() = "The tornado peters out and disappears, leaving only debris behind."

    override fun describeChange(prev: List<Event>): String? = when (prev.firstOrNull { it.type is Tornado }?.type) {
        null -> "Thrashing winds from opposing directions form into a tornado."
        is Snownado -> "The snow dies down, but the tornado keeps rampaging."
        is Firenado -> "The fires die out, but the tornado keeps rampaging."
        else -> null
    }

    open fun persist(event: Event, nextHour: Hour): Event? = if (event.end.isAfter(nextHour.time)) {
        when {
            event.type !is Firenado && nextHour.events.any { it.type is Wildfire } -> Event(
                start = nextHour.time,
                end = event.end,
                type = Firenado()
            ).also { event.end = nextHour.time }
            event.type !is Snownado && nextHour.weather.precipitation.type is Snow -> Event(
                start = nextHour.time,
                end = event.end,
                type = Snownado()
            ).also { event.end = nextHour.time }
            event.type is Firenado || event.type is Snownado -> Event(
                start = nextHour.time,
                end = event.end,
                type = Tornado()
            ).also { event.end = nextHour.time }
            else -> event
        }
    } else {
        val newEvent = Tornado(nextHour)
        when {
            newEvent == null -> null
            newEvent.type::class == this::class -> {
                event.end = newEvent.end
                event
            }
            else -> newEvent
        }
    }

    companion object {
        operator fun invoke(hour: Hour) = if (hour.weather.precipitation.type.isThunder && Thunder.tornado(hour.weather.wind)) {
            val t = hour.time.plusHours((3 d 6).toLong())
            Event(
                start = hour.time,
                end = t,
                type = when {
                    hour.events.any { it.type is Wildfire } -> Firenado()
                    hour.weather.precipitation.type is Snow -> Snownado()
                    else -> Tornado()
                }
            )
        } else null
    }
}
