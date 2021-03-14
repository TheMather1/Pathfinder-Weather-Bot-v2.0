package pathfinder.weatherBot.weather.precipitation.controller.wet

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Intensity.*
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.controller.Controller

object Wet: Controller {
    override operator fun invoke(weather: Weather): Precipitation =  when(weather.hour.day.forecast.biome.intensity){
        LIGHT -> Light(weather)
        MEDIUM -> Medium(weather)
        HEAVY -> Heavy(weather)
    }
}