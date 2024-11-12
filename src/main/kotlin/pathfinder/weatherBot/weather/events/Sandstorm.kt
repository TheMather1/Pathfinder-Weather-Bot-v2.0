package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Weather

open class Sandstorm : EventType<Sandstorm> {
    override fun progress(hour: Hour, weather: Weather, event: Event): Event? {
        TODO("not implemented")
    }

    override fun describeChange(prev: List<Event>): String? {
        TODO("not implemented")
    }

    override fun finished() = "The clouds of sand seemed to have passed us by!"
}
