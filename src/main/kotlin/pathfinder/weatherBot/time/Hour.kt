package pathfinder.weatherBot.time

import pathfinder.weatherBot.weather.Weather
import java.io.Serializable
import java.time.LocalTime

class Hour(val day: Day, val time: LocalTime, prevHour: Hour? = null) : Serializable {
    var temp = day.temperature.tempAtHour(time)
    val weather: Weather = Weather(this, prevHour?.weather)

    private val tempDescriptor = tempThresholds.firstNotNullOf { (t, d) ->
        if (t == null || temp <= t) d
        else null
    }
    val description = describeTemp(prevHour)
        ?.plus("\n") + weather.description

    private fun describeTemp(prevHour: Hour?) = when {
        prevHour == null -> "The temperature is a $tempDescriptor $temp°F."
        tempRise(prevHour.temp) -> "The temperature rises to a $tempDescriptor $temp°F."
        tempFall(prevHour.temp) -> "The temperature drops to a $tempDescriptor $temp°F."
        else -> null
    }

    private fun tempRise(prevTemp: Long) = tempThresholds.any { (t, _) ->
        t != null && prevTemp <= t && temp > t
    }

    private fun tempFall(prevTemp: Long) = tempThresholds.any { (t, _) ->
        t != null && temp <= t && prevTemp > t
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