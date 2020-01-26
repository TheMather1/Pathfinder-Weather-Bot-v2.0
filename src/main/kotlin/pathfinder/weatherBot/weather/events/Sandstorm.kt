package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.weather.Weather

open class Sandstorm : Event<Sandstorm> {
    override fun progress(weather: Weather): Event<Sandstorm>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun description(prev: Sandstorm?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val finished: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}