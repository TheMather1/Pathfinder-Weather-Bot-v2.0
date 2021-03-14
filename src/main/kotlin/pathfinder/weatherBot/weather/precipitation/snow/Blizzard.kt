package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

open class Blizzard(weather: Weather, hours: Long) : HeavySnow(weather, hours) {

    override fun fall() {
        weather.hour.day.forecast.biome.snowLevel += 4
    }

    override fun description(prev: Precipitation?): String {
        TODO("not implemented")
    }

    override val finished: String
        get() = TODO("not implemented")
}