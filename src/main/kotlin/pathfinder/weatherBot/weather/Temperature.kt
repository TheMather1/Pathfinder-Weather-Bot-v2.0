package pathfinder.weatherBot.weather

import jakarta.persistence.Embeddable
import net.dv8tion.jda.api.entities.MessageEmbed

@Embeddable
data class Temperature(var temp: Long) {
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

    fun embedField(prevTemp: Temperature?) = describeChange(prevTemp)?.let {
        MessageEmbed.Field("Temperature", it, false)
    }

    private fun tempRise(prevTemp: Temperature) = tempThresholds.any { (t, _) ->
        t != null && prevTemp.temp <= t && temp > t
    }

    private fun tempFall(prevTemp: Temperature) = tempThresholds.any { (t, _) ->
        t != null && temp <= t && prevTemp.temp > t
    }

    fun warn(weather: Weather): String? {
        val effectiveTemp = temp + weather.precipitation.type.tempAdjust

        return when {
            effectiveTemp > 140 -> """Extreme heat:
    Breathing air deals 1d6 points of fire damage per minute (no save). 1d4 points of nonlethal damage (DC 15, +1 per previous check Fortitude) per 5 minutes, this damage does not recover until you escape the heat. Armor or heavy clothing impart -4 penalty."""
            effectiveTemp > 110 -> """Severe heat:
    1d4 points of nonlethal damage (DC 15, +1 per previous check Fortitude) per 10 minutes, this damage does not recover until you escape the heat. Armor or heavy clothing impart -4 penalty."""
            effectiveTemp > 90 -> """Hot weather:
    1d4 points of nonlethal damage (DC 15, +1 per previous check Fortitude to avoid) per hour, this damage does not recover until you escape the heat. Armor or heavy clothing impart -4 penalty."""
            effectiveTemp < 40 -> """Cold weather:
    1d4 points of nonlethal damage (DC 15, +1 per previous check Fortitude to avoid) per hour, this damage does not recover until you escape the cold. Nonlethal damage causes hypothermia (Fatigued) until cured."""
            effectiveTemp < 0 -> """Severe cold:
    1d4 points of nonlethal damage (DC 15, +1 per previous check Fortitude to avoid) per 10 minutes, this damage does not recover until you escape the cold. Nonlethal damage causes hypothermia (Fatigued) until cured."""
            effectiveTemp < -20 -> """Extreme cold:
    1d6 points of cold damage per minute (no save). 1d4 points of nonlethal damage (DC 15, +1 per previous check Fortitude) per 5 minutes, this damage does not recover until you escape the heat. Nonlethal damage causes frostbite (Fatigued) until cured."""
            else -> null
        }
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