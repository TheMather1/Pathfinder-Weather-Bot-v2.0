package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Wind.SEVERE

open class Sandstorm : EventType {

    override val warn = """Sandstorm:
    Visibility is reduced to 1d10*10 ft. Perception checks take a -6 penalty. Creatures caught in the open take 1d3 nonlethal damage per hour."""

    override fun describeChange(prev: List<Event>): String? {
        return when {
            prev.any { it.type is Haboob } -> "As the static electricity dissipates, the haboob loses cohesion and collapses into a sandstorm."
            prev.any { it.type is Sandstorm } -> null
            else -> "Intense wind kick up dust and sand, causing a raging sandstorm."
        }
    }

    override fun finished() = "The airborne sand slowly settles."

    open fun persist(event: Event, nextHour: Hour) = if (nextHour.weather.wind >= SEVERE) {
        event.end = nextHour.time.plusHours(1)
        event
    } else null

    companion object {
        private fun condition(hour: Hour) = hour.weather.wind >= SEVERE
        operator fun invoke(hour: Hour) = if (condition(hour)) Event(
            start = hour.time,
            end = hour.time.plusHours(1),
            type = Sandstorm()
        ) else null
    }
}
