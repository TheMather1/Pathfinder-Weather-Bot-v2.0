package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import pathfinder.weatherBot.weather.precipitation.rain.Thunderstorm
import java.time.LocalDateTime

class Thundersnow(start: LocalDateTime, end: LocalDateTime) : HeavySnow(start, end), Thunder {
    companion object {
        operator fun invoke(start: LocalDateTime, end: LocalDateTime): HeavySnow = Thunder.wind.let {
            if (blizzard(it)) ThunderBlizzard(start, end)
            else Thundersnow(start, end)
        }
    }

    override fun description(prev: Precipitation?) = when (prev) {
        is Thundersnow -> null
        is ThunderBlizzard -> "The blizzard ceases as the winds calm, but the snowfall and thunder still linger."
        is Thunderstorm -> "The rain freezes into a heavy snowfall, as thunder keeps roaring out in the distance."
        is HeavySnow -> "Muffled slightly by the heavy blanket of snow, thunder roars out in the distance."
        is Snow -> "The snow picks up into a heavy snowfall, as thunder roars out in the distance."
        else -> "A heavy blanketing of snow starts to fall, accompanied by thunder and lightning."
    }?.plus(" Be careful about walking outside in metal armor.")

    override val finished: String
        get() = "The thunder rolls away, as does the snow."
}