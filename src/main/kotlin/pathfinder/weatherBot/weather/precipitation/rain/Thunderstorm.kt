package pathfinder.weatherBot.weather.precipitation.rain

import pathfinder.weatherBot.weather.precipitation.None
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import pathfinder.weatherBot.weather.precipitation.fog.Fog
import pathfinder.weatherBot.weather.precipitation.snow.Snow
import java.time.LocalDateTime

class Thunderstorm(start: LocalDateTime, end: LocalDateTime) : HeavyRain(start, end),
    Thunder {
    override fun print(prev: Precipitation?) = when (prev) {
        is Thunderstorm -> null
        is Fog -> "With a earth-shaking boom, a thunderstorm scares off the fog."
        is HeavyRain -> "The downpour grows into a thunderstorm!"
        is Rain -> "The rain suddenly begins pouring harder, and white streaks of lightning generate from the clouds."
        is Snow -> "The rising temperatures cause the snowfall to escalate into a fully-fledged thunderstorm!"
        is None -> "With blackened clouds pouring to set the mood, a white flash and loud cracks from the sky, it's apparent that a thunderstorm is happening!"
        else -> "There is a roaring thunderstorm."
    }?.plus(" Be careful about walking outside in metal armor.")

    override val finished: String
        get() = "The sounds of thunder cease, and soon after, the shower as well."

    override fun fall() {
        TODO("not implemented")
    }
}
