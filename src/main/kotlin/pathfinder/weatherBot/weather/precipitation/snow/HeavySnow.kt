package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.d
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.rain.Rain


open class HeavySnow(hour: Hour, hours: Long) : Snow(hour, hours) {
    override val fireRetardance = 75

    companion object {
        fun blizzard(wind: Wind): Boolean = wind >= Wind.SEVERE && (1 d 100) <= 40
        operator fun invoke(hour: Hour, hours: Long): HeavySnow {
            val wind = Wind()
            fun blizzardDuration() = if ((1 d 100) <= 20) 2 d 12 else hours
            return when {
                thunder() -> Thundersnow(hour, hours)
                blizzard(wind) -> Blizzard(hour, blizzardDuration())
                else -> HeavySnow(hour, hours)
            }
        }
    }

    override fun fall() {
//        hour.day.forecast.biome.snowLevel += (1 d 4)
    }

    override fun description(prev: Precipitation?) = when(prev) {
        is Blizzard -> "The blizzard yields, but the air is still heavy with snow."
        is HeavySnow -> null
        is Snow -> "The snow intensifies into a thick blanket in the air, piling onto the ground."
        is Rain -> "The rain freezes into a heavy blanket of snow, which starts piling up on the ground."
        is Fog -> "The fog gives way to a heavy blanket of snow, which starts piling up on the ground."
        else -> "The air is heavy with snow, which rapidly piles up on the ground."
    }

    override val finished: String
        get() = "The heavy pillowing of snow stops."
}