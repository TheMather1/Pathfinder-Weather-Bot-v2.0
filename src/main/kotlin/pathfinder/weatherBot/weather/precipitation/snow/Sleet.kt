package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

class Sleet(weather: Weather, hours: Long) : Snow(weather, hours) {
    override val fireRetardance = 25
    override fun description(prev: Precipitation?): String {
        TODO("not implemented")
    }

    override val finished: String
        get() = TODO("not implemented")

    override fun fall() {
        TODO("not implemented")
    }
}