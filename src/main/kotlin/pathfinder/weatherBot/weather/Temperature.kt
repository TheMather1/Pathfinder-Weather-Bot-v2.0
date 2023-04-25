package pathfinder.weatherBot.weather

import java.io.Serializable

data class Temperature(var temp: Long): Serializable {
    val freezing
        get() = temp <= 32
    val cold
        get() = !freezing && temp < 40

    val evaporationMod
        get() = (70 - temp).times(0.001F).coerceAtMost(0F)

    private val descriptor
        get() = tempThresholds.firstNotNullOf { (t, d) ->
            if (t == null || temp <= t) d
            else null
        }

    override fun toString(): String {
        return "$temp°F"
    }

    fun describeChange(prevTemp: Temperature?) = when {
        prevTemp == null -> "The temperature is a $descriptor $this."
        tempRise(prevTemp) -> "The temperature rises to a $descriptor $this."
        tempFall(prevTemp) -> "The temperature drops to a $descriptor $this."
        else -> null
    }

    private fun tempRise(prevTemp: Temperature) = tempThresholds.any { (t, _) ->
        t != null && prevTemp.temp <= t && temp > t
    }

    private fun tempFall(prevTemp: Temperature) = tempThresholds.any { (t, _) ->
        t != null && temp <= t && prevTemp.temp > t
    }

    companion object {
        private val tempThresholds = mapOf(
            32 to "freezing",
            40 to "cold",
            65 to "chilly",
            85 to "comfortable",
            105 to "warm",
            120 to "sweltering",
            null to "scorching"
        )
    }
}