package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Weather
import java.time.LocalDateTime

open class Sandstorm(override val start: LocalDateTime, override val end: LocalDateTime) : Event<Sandstorm> {
    override fun progress(hour: Hour, weather: Weather): Event<Sandstorm>? {
        TODO("not implemented")
    }

    override fun description(prev: List<Event<*>>): String? {
        TODO("not implemented")
    }

    override val finished = "The clouds of sand seemed to have passed us by!"
}