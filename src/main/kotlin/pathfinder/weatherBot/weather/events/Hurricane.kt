package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.weather.Weather

class Hurricane : Event<Hurricane> {
    override fun progress(weather: Weather): Event<Hurricane>? {
        TODO("not implemented")
    }

    override val finished = TODO()
    override fun description(prev: Hurricane?): String {
        TODO("not implemented")
    }
}