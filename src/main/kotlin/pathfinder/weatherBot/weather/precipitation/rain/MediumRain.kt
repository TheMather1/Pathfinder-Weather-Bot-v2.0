package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

class MediumRain(weather: Weather, hours: Long) : Rain(weather, hours) {
    override val fireRetardance = 15
    override fun description(prev: Precipitation?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val finished: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}