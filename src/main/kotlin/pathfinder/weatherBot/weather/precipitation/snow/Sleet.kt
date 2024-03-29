package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.precipitation.None
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.rain.Rain
import java.time.LocalDateTime

class Sleet(start: LocalDateTime, end: LocalDateTime) : Snow(start, end) {
    override val fireRetardance = 25
    override fun describeChange(prev: Precipitation?) = when(prev) {
        is Sleet -> null
        is Blizzard -> "The blizzard yields as temperatures pick up, the fierce snowstorm giving way to frosty sleet."
        is Snow -> "The snowfall begins to melt into a wet sleet."
        is Rain -> "The rain partially freezes into a wet sleet."
        is Fog -> "The fog is washed away as wet sleet begins to fall."
        is None -> "Wet sleet begins dusting the area."
        else -> "Wet sleet is falling through the air."
    }

    override val finished: String
        get() = "The sleet came and went, leaving very few traces of its occurrence behind."

    override fun fall() {
        TODO("not implemented")
    }
}
