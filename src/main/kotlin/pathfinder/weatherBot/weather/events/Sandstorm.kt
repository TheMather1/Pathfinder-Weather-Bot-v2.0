package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.weather.Weather

open class Sandstorm : Event<Sandstorm> {
    override fun progress(weather: Weather): Event<Sandstorm>? {
        TODO("not implemented")
    }

    override fun description(prev: Sandstorm?): String {
        TODO("not implemented")
    }

    override val finished = "The clouds of sand seemed to have passed us by!"
}