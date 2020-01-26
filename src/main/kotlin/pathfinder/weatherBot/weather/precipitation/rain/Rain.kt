package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

abstract class Rain(weather: Weather, hours: Long) : Precipitation(weather, hours) {
    override fun fall() {
        TODO()
    }
}