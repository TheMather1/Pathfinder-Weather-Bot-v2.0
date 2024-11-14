package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.Wind.SEVERE

open class Sandstorm : EventType<Sandstorm> {
    override fun progress(hour: Hour, weather: Weather, event: Event) = if (event.end.isAfter(hour.time) && hour.weather.wind >= SEVERE) event
    else null

    override val warn = """Sandstorm:
    Visibility is reduced to 1d10*10 ft. Perception checks take a -6 penalty. Creatures caught in the open take 1d3 nonlethal damage per hour."""

    override fun describeChange(prev: List<Event>): String? {
        return when {
            prev.any { it.type is Haboob } -> "As the static electricity dissipates, the haboob loses cohesion and collapses into a sandstorm."
            prev.any { it.type is Sandstorm } -> null
            else -> "Intense wind kick up dust and sand, causing a raging sandstorm."
        }
    }

    override fun finished() = "The clouds of sand seemed to have passed us by!"
}
