package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.weather.Weather

class Hurricane : Event<Hurricane> {
    override fun progress(weather: Weather): Event<Hurricane>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val finished = TODO()
    override fun description(prev: Hurricane?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}