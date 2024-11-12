package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Weather

class Haboob : Sandstorm() {
    override fun progress(hour: Hour, weather: Weather, event: Event): Event? {
        return if (event.end.isAfter(hour.time)) event else null
    }

    override fun describeChange(prev: List<Event>): String? {
        return if (prev.none { it.type is Sandstorm }) "An oppressively massive flux of dust and sand appears to have been kicked up by the wind from the desert. The cloud of fine debris is visible on the horizon and making its way here!"
        else null
    }

    override fun finished() = "The clouds of sand seemed to have passed us by!"
}