package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.Weather
import java.time.LocalDateTime

interface Event {
    companion object {
        operator fun invoke(prevEvents: List<Event>, time: LocalDateTime, weather: Weather, location: Location): List<Event> {
            return prevEvents.mapNotNull { it.progress(time) }.apply {
                if (none { it is Wildfire}) Wildfire(weather.temp(time.toLocalTime()), weather.precipitation(time.toLocalTime()), location)
            }
        }
    }

    fun progress(time: LocalDateTime): Event?

    val description: String
    val finished: String
}