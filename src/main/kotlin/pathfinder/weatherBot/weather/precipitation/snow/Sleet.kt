package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

class Sleet(weather: Weather, hours: Long) : Snow(weather, hours) {
    override val fireRetardance = 25
    override fun description(prev: Precipitation?) = "Icy sleet begins dusting the area."

    override val finished: String
        get() = "The sleet came and went, leaving very few traces of its occurrence behind."

    override fun fall() {
        TODO("not implemented")
    }
}