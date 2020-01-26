package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

class LightSnow(weather: Weather, hours: Long) : Snow(weather, hours) {
    override val fireRetardance = 5
    override fun fall() {
        weather.hour.day.forecast.biome.snowLevel += 0.5
    }

    override fun description(prev: Precipitation?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val finished: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}