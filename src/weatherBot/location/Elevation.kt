package weatherBot.location

import weatherBot.precipitation.Intensity

enum class Elevation {
    SEA_LEVEL{
        val adjustTemperature = 10
        val adjustPrecipitation = 0
        val basePrecipitation = Intensity.HEAVY
    },
    LOWLAND{
        val adjustTemperature = 0
        val adjustPrecipitation = 0
        val basePrecipitation = Intensity.MEDIUM
    },
    HIGHLAND{
        val adjustTemperature = -10
        val adjustPrecipitation = -1
        val basePrecipitation = Intensity.MEDIUM
    },
    HIGHLAND_ARID{
        val adjustTemperature = +10
        val adjustPrecipitation = -1
        val basePrecipitation = Intensity.MEDIUM
    },
    HIGHLAND_MOUNTAIN{
        val adjustTemperature = -20
        val adjustPrecipitation = -1
        val basePrecipitation = Intensity.MEDIUM
    }
}