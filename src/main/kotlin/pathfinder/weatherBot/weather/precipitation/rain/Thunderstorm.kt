package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder

class Thunderstorm(weather: Weather, hours: Long) : HeavyRain(weather , hours),
        Thunder {
    override fun description(prev: Precipitation?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val finished: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun fall() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}