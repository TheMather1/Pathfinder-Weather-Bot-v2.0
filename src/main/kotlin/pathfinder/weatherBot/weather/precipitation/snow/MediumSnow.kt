package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.rain.Rain

class MediumSnow(hour: Hour, hours: Long) : Snow(hour, hours) {
    override val fireRetardance = 25

    override fun fall() {
        hour.day.forecast.biome.snowLevel += 1
    }

    override fun description(prev: Precipitation?) = when(prev) {
        is MediumSnow -> null
        is Blizzard -> "The blizzard yields, and only a moderate snowfall remains."
        is HeavySnow -> "The snowfall slows slightly, but is still rapidly blanketing the ground."
        is Snow -> "The snowfall picks up, and is now rapidly blanketing the ground."
        is Rain -> "The rain freezes into a considerable snowfall, rapidly blanketing the ground."
        is Fog -> "The fog dissipates as a considerable snowfall begins rapidly blanketing the ground."
        else -> "Snow begins falling from the sky and begins rapidly blanketing the ground."
    }


    override val finished: String
        get() = "The snowing gradually ends."
}