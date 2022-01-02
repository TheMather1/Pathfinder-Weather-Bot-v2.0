package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.rain.Rain

open class Blizzard(hour: Hour, hours: Long) : HeavySnow(hour, hours) {

    override fun fall() {
//        hour.day.forecast.biome.snowLevel += 4
    }

    override fun description(prev: Precipitation?) = when(prev) {
        is Blizzard -> null
        is Snow -> "Intense winds turns the snow into a blizzard."
        is Rain -> "The rain freezes as a blizzard picks up."
        is Fog -> "The fog is blown away as a blizzard picks up."
        else -> "Heavy snow and high winds combine into a blizzard."
    }

    override val finished: String
        get() = "Leaving behind a heaping helping of snow, the blizzard has passed us!"
}