package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.precipitation.None
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import java.time.LocalDateTime

class ThunderBlizzard(start: LocalDateTime, end: LocalDateTime) : Blizzard(start, end), Thunder {
    override fun print(prev: Precipitation?) = when (prev) {
        is ThunderBlizzard -> null
        is Blizzard -> "The blizzard holds strong as thunder starts to roar out in the distance."
        is Thundersnow -> "Intense winds turn the thundersnow into a thunder blizzard."
        is Snow -> "Intense winds turns the snow into a blizzard as thunder roars out in the distance."
        is None -> "An intense blizzard rolls in as thunder roars out in the distance"
        else -> "An intense blizzard is accompanied by roaring thunder."
    }?.plus(" Be careful about walking outside in metal armor.")

    override val finished: String
        get() = "The thunder blizzard clears out."
}
