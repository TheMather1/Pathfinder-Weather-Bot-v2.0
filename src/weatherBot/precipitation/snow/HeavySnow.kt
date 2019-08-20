package weatherBot.precipitation.snow

import weatherBot.d
import weatherBot.precipitation.Precipitation

interface HeavySnow: Snow {
    fun thunder(): Boolean = (1 d 100) <= 10
    private fun blizzard(): Boolean = (1 d 100) <= 40

    fun invoke(duration: Int){
        when {
            blizzard() -> TODO("Unresolved bug.")//Blizzard()
            thunder() -> Thundersnow(duration)
            else -> HeavySnowImpl(duration)
        }
    }
}