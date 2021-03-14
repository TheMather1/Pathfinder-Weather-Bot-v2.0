package pathfinder.weatherBot.weather.precipitation.controller

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

interface Controller {
    operator fun invoke(weather: Weather): Precipitation
}