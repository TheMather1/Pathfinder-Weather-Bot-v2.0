package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.rain.Rain
import pathfinder.weatherBot.weather.precipitation.snow.Snow
import java.time.LocalDateTime

class LightFog(start: LocalDateTime, end: LocalDateTime) : Fog(start, end) {

    override val fireRetardance = 0

    override fun description(prev: Precipitation?) = when (prev) {
        is LightFog -> null
        is Fog -> "The fog fades to a thin veil."
        is Rain -> "As the clouds empty, a light mist hangs in the air..."
        is Snow -> "The snowfall relents and a fine mist remains in its place."
        else -> "A fine mist falls upon the area."
    }?.plus(" (Sight is reduced to 3/4 range, and you suffer a -2 to perception and ranged attacks.)")

    override val finished: String
        get() = "The mist dissipates, we can see clearly now!"
}