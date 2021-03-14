package pathfinder.weatherBot.weather.precipitation.controller.frozen

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Intensity
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.controller.Controller

object Frozen: Controller {
    override operator fun invoke(weather: Weather): Precipitation = when(weather.hour.day.forecast.biome.intensity){
            Intensity.LIGHT -> Light(weather)
            Intensity.MEDIUM -> Medium(weather)
            Intensity.HEAVY -> Heavy(weather)
        }
}