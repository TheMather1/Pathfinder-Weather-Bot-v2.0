package weatherBot.precipitation

import weatherBot.d
import weatherBot.location.Location
import weatherBot.precipitation.Precipitation.Companion.thunder
import weatherBot.precipitation.controller.frozen.Frozen
import weatherBot.precipitation.controller.wet.Wet
import weatherBot.precipitation.snow.*
import weatherBot.time.Season

interface Precipitation {
    companion object{
        private fun dry(location: Location, season: Season): Boolean = (1 d 100) > season.frequency(location).chance
        private fun frozen(temp: Int): Boolean = temp <= 32
        fun thunder(): Boolean = (1 d 100) <= 10

        operator fun invoke(location: Location, season: Season, temp: Int): Precipitation? = when {
            dry(location, season) -> null
            frozen(temp) -> Frozen(location.intensity)
            else -> Wet(location.intensity)
        }
    }
    val duration: Int
    val tempAdjust: Int
        get() = 0
}

interface Snow: Precipitation
interface HeavySnow: Snow {
    companion object{
        private fun blizzard(): Boolean = (1 d 100) <= 40
        operator fun invoke(duration: Int): HeavySnow {
            fun blizzardDuration(): Int = if ((1 d 100) <= 20) 2 d 12 else duration
            return when {
                blizzard() -> Blizzard(blizzardDuration())
                thunder() -> Thundersnow(duration)
                else -> HeavySnowImpl(duration)
            }
        }
    }
}
interface Blizzard : HeavySnow{
    companion object {
        operator fun invoke(duration: Int): HeavySnow {
            return if (thunder()) ThunderBlizzard(duration)
            else BlizzardImpl(duration)
        }
    }
}