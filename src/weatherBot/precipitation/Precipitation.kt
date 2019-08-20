package weatherBot.precipitation

import weatherBot.precipitation.controller.frozen.Frozen

interface Precipitation {
    val duration: Int

    private fun frozen(): Boolean = temperature <= 32

    fun invoke(frequency: Frequency, intensity: Intensity) =
        if (frozen()) Frozen.getPrecipitation(intensity)
        else Wet.getPrecipitation(intensity)
}