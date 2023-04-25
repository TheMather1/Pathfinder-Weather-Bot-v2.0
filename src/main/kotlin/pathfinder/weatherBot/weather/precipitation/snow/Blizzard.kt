package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.None
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.rain.Rain
import java.time.LocalDateTime

open class Blizzard(start: LocalDateTime, end: LocalDateTime, wind: Wind) : HeavySnow(start, end, wind) {

    override fun fall() {
//        hour.day.forecast.biome.snowLevel += 4
    }

    override fun describeChange(prev: Precipitation?) = when(prev) {
        is Blizzard -> null
        is Snow -> "Intense winds turns the snow into a blizzard."
        is Rain -> "The rain freezes as a blizzard picks up."
        is Fog -> "The fog is blown away as a blizzard picks up."
        is None -> "Heavy snow and high winds combine into a blizzard."
        else -> "There is a blizzard buffeting the area."
    }

    override val finished: String
        get() = "Leaving behind a heaping helping of snow, the blizzard has passed us!"
}
