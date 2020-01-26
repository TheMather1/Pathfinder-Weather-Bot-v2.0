package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

abstract class Fog(weather: Weather, hours: Long) : Precipitation(weather, hours) {
    override fun fall() {
        TODO()
    }
}