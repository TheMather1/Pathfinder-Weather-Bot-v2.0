package pathfinder.weatherBot.weather.events

import pathfinder.diceSyntax.d
import pathfinder.weatherBot.time.Hour

class Haboob : Sandstorm() {

    override fun describeChange(prev: List<Event>): String? {
        return when {
            prev.any { it.type is Haboob } -> null
            prev.any { it.type is Sandstorm } -> "Crackling static causes the sandstorm to coalesce into a haboob."
            else -> "An oppressively massive flux of dust and sand has been kicked up by the wind from the desert. The cloud of fine debris and static is making its way across the landscape!"
        }
    }

    override fun finished() = "The clouds of sand scatter and dissipate."

    override fun persist(event: Event, nextHour: Hour) = if (nextHour.weather.precipitation.type.isThunder) {
        event.end = nextHour.time.plusHours(1)
        event
    } else null

    companion object {
        private fun condition(hour: Hour) = hour.weather.precipitation.type.isThunder && (1 d 100).toInt() <= 20
        operator fun invoke(hour: Hour) = if (condition(hour)) Event(
            start = hour.time,
            end = hour.time.plusHours(1),
            type = Haboob()
        ) else null
    }
}