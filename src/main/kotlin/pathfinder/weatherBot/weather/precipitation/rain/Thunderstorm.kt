package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder

class Thunderstorm(weather: Weather, hours: Long) : HeavyRain(weather , hours),
        Thunder {
    override fun description(prev: Precipitation?): String {
        TODO("not implemented")
    }

    override val finished: String
        get() = TODO("not implemented")

    override fun fall() {
        TODO("not implemented")
    }
}