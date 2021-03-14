package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

class HeavyFog(weather: Weather, hours: Long) : Fog(weather, hours) {
    override val fireRetardance = 10
    override fun description(prev: Precipitation?): String {
        "An incredibly thick fog falls upon the land. (All vision beyond 5 feet is obscured. Creatures 5 feet away have concealment.)"
    }

    override val finished: String
        get() = "The obscuring fog yields, we can see! WE CAN FIGHT!"