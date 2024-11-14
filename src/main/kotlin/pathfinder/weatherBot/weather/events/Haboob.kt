package pathfinder.weatherBot.weather.events

import pathfinder.diceSyntax.d
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Weather

class Haboob : Sandstorm() {
    override fun progress(hour: Hour, weather: Weather, event: Event): Event? {
        return if (event.end.isAfter(hour.time)) event else null
    }

    override fun describeChange(prev: List<Event>): String? {
        return when {
            prev.any { it.type is Haboob } -> null
            prev.any { it.type is Sandstorm } -> "Crackling static causes the sandstorm to coalesce into a haboob."
            else -> "An oppressively massive flux of dust and sand appears to have been kicked up by the wind from the desert. The cloud of fine debris is visible on the horizon and making its way here!"
        }
    }

    override fun finished() = "The clouds of sand seemed to have passed us by!"

    companion object {
        operator fun invoke(hour: Hour) = if (hour.weather.precipitation.type.isThunder && (1 d 100).toInt() <= 20) Event(
            start = hour.time,
            end = hour.weather.precipitation.end,
            type = Haboob()
        ) else null
    }
}