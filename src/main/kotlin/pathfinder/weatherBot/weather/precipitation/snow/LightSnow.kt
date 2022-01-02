package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.rain.Rain

class LightSnow(hour: Hour, hours: Long) : Snow(hour, hours) {
    override val fireRetardance = 5
    override fun fall() {
//        hour.day.forecast.biome.snowLevel += 0.5
    }

    override fun description(prev: Precipitation?) = when(prev) {
        is LightSnow -> null
        is Blizzard -> "The blizzard yields, and only a light snowfall remains."
        is Sleet -> "The sleet freezes into a light dusting of snow."
        is Snow -> "The snow slows to a gentle dusting."
        is Rain -> "The rain freezes into a light snow."
        is Fog -> "The fog dissipates as a light dusting of snow begins to fall."
        else -> "Snowflakes flutter down from the sky, gently dusting the ground."
    }


    override val finished: String
        get() = "The gentle snowing has ended."
}