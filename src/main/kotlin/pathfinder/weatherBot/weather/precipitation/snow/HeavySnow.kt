package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.d
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.None
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.rain.Rain
import java.time.LocalDateTime


open class HeavySnow(start: LocalDateTime, end: LocalDateTime) : Snow(start, end) {
    override val fireRetardance = 75

    companion object {
        fun blizzard(wind: Wind): Boolean = wind >= Wind.SEVERE && (1 d 100) <= 40
        operator fun invoke(start: LocalDateTime, end: LocalDateTime, hour: Hour): HeavySnow {
            val wind = Wind()
            fun blizzardDuration() = if ((1 d 100) <= 20) hour.time.plusHours(2 d 12) else end
            return when {
                thunder() -> Thundersnow(start, end)
                blizzard(wind) -> Blizzard(start, blizzardDuration())
                else -> HeavySnow(start, end)
            }
        }
    }

    override fun fall() {
//        hour.day.forecast.biome.snowLevel += (1 d 4)
    }

    override fun print(prev: Precipitation?) = when(prev) {
        is Blizzard -> "The blizzard yields, but the air is still heavy with snow."
        is HeavySnow -> null
        is Snow -> "The snow intensifies into a thick blanket in the air, piling onto the ground."
        is Rain -> "The rain freezes into a heavy blanket of snow, which starts piling up on the ground."
        is Fog -> "The fog gives way to a heavy blanket of snow, which starts piling up on the ground."
        is None -> "The air is heavy with snow, which rapidly piles up on the ground."
        else -> "An intense snowstorm blankets the area."
    }

    override val finished: String
        get() = "The heavy pillowing of snow stops."
}
