package pathfinder.weatherBot.weather.precipitation.fog

import pathfinder.weatherBot.weather.precipitation.None
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.rain.Rain
import pathfinder.weatherBot.weather.precipitation.snow.Snow
import java.time.LocalDateTime

class MediumFog(start: LocalDateTime, end: LocalDateTime) : Fog(start, end) {
    override val fireRetardance = 5
    override fun print(prev: Precipitation?) = when (prev) {
        is MediumFog -> null
        is LightFog -> "The mist thickens into a fully-fledged fog."
        is HeavyFog -> "The thick haze fades slightly into a moderate fog."
        is Rain -> "The rain passes as a fog falls upon the area."
        is Snow -> "As the snow stops, it gives way to a fog."
        is None -> "An obscuring fog rolls in."
        else -> "The area is covered in fog."
    }?.plus(" (Sight is reduced 1/2 range. Take a -4 to perception and ranged attack rolls.)")

    override val finished: String
        get() = "The fog fades away, sight is no longer obscured."
}
