package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.d
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.rain.Thunderstorm
import java.time.LocalDateTime

open class Wildfire(private val location: Location) : Event {
    companion object {
        operator fun invoke(temp: Long, precipitation: Precipitation?, location: Location): Wildfire? {
            return location.groundMoisture.toInt().let {
                if (
                        (temp - 90 - it - (precipitation?.fireRetardance ?: 0)) / 2 <= 100
                        || (precipitation is Thunderstorm && (1 d 100) <= 60 - it)
                ) Wildfire(location) else null
            }
        }
    }

    override fun progress(time: LocalDateTime): Event? {
        TODO("extinguish?")
    }
    override val description = TODO()
    override val finished = TODO()
}