package pathfinder.weatherBot.weather.precipitation

import java.time.LocalDateTime

class None(start: LocalDateTime) : Precipitation(start, start.plusHours(1)) {
    override fun fall() { /*Do nothing*/ }

    override val fireRetardance: Int = 0

    override fun print(prev: Precipitation?): String?  = prev?.takeUnless { it is None }?.finished

    override val finished: String
        get() = throw IllegalStateException("Precipitation type None has no end description.")
}
