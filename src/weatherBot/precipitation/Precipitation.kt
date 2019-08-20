package weatherBot.precipitation

import weatherBot.d
import weatherBot.precipitation.controller.frozen.Frozen
import weatherBot.precipitation.controller.wet.Wet
import weatherBot.precipitation.snow.*

interface Precipitation {
    val duration: Int

    private fun frozen(): Boolean = true//temperature <= 32

    fun invoke(frequency: Frequency, intensity: Intensity) =
        if (frozen()) Frozen(intensity)
        else Wet(intensity)
}

interface Snow: Precipitation
interface HeavySnow: Snow
interface Blizzard : HeavySnow

object HeavySnowFactory{
    fun thunder(): Boolean = (1 d 100) <= 10
    private fun blizzard(): Boolean = (1 d 100) <= 40

    object BlizzardFactory {
        operator fun invoke(duration: Int): HeavySnow {
            return if (thunder()) ThunderBlizzard(duration)
            else BlizzardImpl(duration)
        }
    }

    operator fun invoke(duration: Int): HeavySnow {
        fun blizzardDuration(): Int = if ((1 d 100) <= 20) 2 d 12 else duration
        return when {
            blizzard() -> BlizzardFactory(blizzardDuration())
            thunder() -> Thundersnow(duration)
            else -> HeavySnowImpl(duration)
        }
    }
}