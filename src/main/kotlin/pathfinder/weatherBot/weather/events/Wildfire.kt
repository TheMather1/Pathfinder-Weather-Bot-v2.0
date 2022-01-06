package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.d
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.rain.Thunderstorm
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.HOURS

open class Wildfire(override val start: LocalDateTime, override var end: LocalDateTime) : Event<Wildfire> {
    companion object {
        operator fun invoke(hour: Hour): Wildfire? {
            return if (hour.hotAndDry() || hour.lightningSpark()) Wildfire(
                hour.time, hour.time.plusHours(1)
            ) else null
        }

        private fun Hour.hotAndDry() = (1 d 100) <= fireRisk

        private fun Hour.lightningSpark() = weather.precipitation is Thunderstorm && (1 d 100) >= 60 - (humidity * 100)
    }

    override fun progress(hour: Hour, weather: Weather): Event<Wildfire>? =
        if ( (1 d 10) + hour.fireRisk > start.until(hour.time, HOURS)) {
            end = end.plusHours(1)
            this
        } else null

    override fun description(prev: List<Event<*>>): String? =
        if(prev.any { it is Wildfire }) "The wildfire keeps raging."
        else "A wildfire erupts in the distance and quickly swallows the area."

    override val finished = "The wildfire is extinguished."
}