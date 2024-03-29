package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.precipitation.None
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.rain.Rain
import java.time.LocalDateTime

class LightSnow(start: LocalDateTime, end: LocalDateTime) : Snow(start, end) {
    override val fireRetardance = 5
    override fun fall() {
//        hour.day.forecast.biome.snowLevel += 0.5
    }

    override fun describeChange(prev: Precipitation?) = when(prev) {
        is LightSnow -> null
        is Blizzard -> "The blizzard yields, and only a light snowfall remains."
        is Sleet -> "The sleet freezes into a light dusting of snow."
        is Snow -> "The snow slows to a gentle dusting."
        is Rain -> "The rain freezes into a light snow."
        is Fog -> "The fog dissipates as a light dusting of snow begins to fall."
        is None -> "Snowflakes flutter down from the sky, gently dusting the ground."
        else -> "Fine snow dances in the air."
    }


    override val finished: String
        get() = "The gentle snowing has ended."
}
