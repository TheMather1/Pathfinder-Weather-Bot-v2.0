package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation

class MediumSnow(weather: Weather, hours: Long) : Snow(weather, hours) {
    override val fireRetardance = 25

    override fun fall() {
        weather.hour.day.forecast.biome.snowLevel += 1
    }

    override fun description(prev: Precipitation?): String {
        "Snow begins falling from the sky with some haste. With any luck, the children will have a snow day!"
    }

    override val finished: String
        get() = "The snowing gradually ends."
}