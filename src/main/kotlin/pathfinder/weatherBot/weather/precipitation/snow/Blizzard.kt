package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

open class Blizzard(weather: Weather, hours: Long) : HeavySnow(weather, hours) {

    override fun fall() {
        weather.hour.day.forecast.biome.snowLevel += 4
    }

    override fun description(prev: Precipitation?): String {
        "Heavy snow and high winds aren't a good combination. A blizzard has descended upon us!"
    }

    override val finished: String
        get() = "Leaving behind a heaping helping of snow, the blizzard has passed us!"
}