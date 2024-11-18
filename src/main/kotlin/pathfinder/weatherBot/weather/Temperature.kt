package pathfinder.weatherBot.weather

import jakarta.persistence.Embeddable
import net.dv8tion.jda.api.entities.MessageEmbed

@Suppress("JpaObjectClassSignatureInspection")
@Embeddable
data class Temperature(var temp: Long) {
    val freezing
        get() = temp <= 32
    val cold
        get() = !freezing && temp < 40

    val evaporationMod
        get() = (70 - temp).times(0.001F).coerceAtMost(0F)

    val descriptor: String
        get() = when {
            temp > 140 -> "scorching"
            temp > 110 -> "blistering"
            temp > 90 -> "sweltering"
            temp > 75 -> "warm"

            temp < -20 -> "numbing"
            temp < 0 -> "freezing"
            temp < 40 -> "frigid"
            temp < 55 -> "cold"
            temp < 65 -> "chilly"

            else -> "comfortable"
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

    private fun tempRise(prevTemp: Temperature) = temp > prevTemp.temp && tempChange(prevTemp)

    private fun tempFall(prevTemp: Temperature) = temp < prevTemp.temp && tempChange(prevTemp)

    private fun tempChange(prevTemp: Temperature) = descriptor != prevTemp.descriptor

    fun warn(weather: Weather): String? {
        val effectiveTemp = temp + weather.precipitation.type.tempAdjust

        return when {
            effectiveTemp > 140 -> """Extreme heat:
    Breathing air deals 1d6 points of fire damage per minute (no save). 1d4 points of nonlethal damage (DC 15, +1 per previous check Fortitude) per 5 minutes, this damage does not recover until you escape the heat. Armor or heavy clothing impart a -4 penalty."""
            effectiveTemp > 110 -> """Severe heat:
    1d4 points of nonlethal damage (DC 15, +1 per previous check Fortitude) per 10 minutes, this damage does not recover until you escape the heat. Armor or heavy clothing impart a -4 penalty."""
            effectiveTemp > 90 -> """Hot weather:
    1d4 points of nonlethal damage (DC 15, +1 per previous check Fortitude to avoid) per hour, this damage does not recover until you escape the heat. Armor or heavy clothing impart a -4 penalty."""
            effectiveTemp < -20 -> """Extreme cold:
    1d6 points of cold damage per minute (no save). 1d4 points of nonlethal damage (DC 15, +1 per previous check Fortitude) per 5 minutes, this damage does not recover until you escape the heat. Nonlethal damage causes frostbite (Fatigued) until cured."""
            effectiveTemp < 0 -> """Severe cold:
    1d4 points of nonlethal damage (DC 15, +1 per previous check Fortitude to avoid) per 10 minutes, this damage does not recover until you escape the cold. Nonlethal damage causes hypothermia (Fatigued) until cured."""
            effectiveTemp < 40 -> """Cold weather:
    1d4 points of nonlethal damage (DC 15, +1 per previous check Fortitude to avoid) per hour, this damage does not recover until you escape the cold. Nonlethal damage causes hypothermia (Fatigued) until cured."""
            else -> null
        }
    }
}