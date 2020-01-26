package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.d
import pathfinder.weatherBot.weather.Weather

class Hail : Event<Hail> {
    override fun progress(weather: Weather): Event<Hail>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val finished = TODO()
    private val damaging = (1 d 100) <= 5
    override fun description(prev: Hail?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}