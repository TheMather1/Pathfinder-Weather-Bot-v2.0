package pathfinder.weatherBot.time

import jakarta.persistence.ElementCollection
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jdk.jfr.Percentage
import pathfinder.weatherBot.weather.Temperature
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.events.Event
import pathfinder.weatherBot.weather.precipitation.None
import java.time.LocalDateTime

@Entity(name = "HOURS")
class Hour(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val time: LocalDateTime,
    @Embedded
    var temp: Temperature,
    @Embedded
    val weather: Weather,
    @Percentage
    var humidity: Float = 1F,
    @ElementCollection
    val events: MutableList<Event>
) {

    val fireRisk: Float
        get() = (temp.temp - 100 - (weather.precipitation.type.fireRetardance)) * (1 - humidity)

    private fun describeEvents(prevEvents: List<Event>) =
        listOfNotNull(events.filter { it.active }.mapNotNull { e -> e.type.describeChange(prevEvents) },
            prevEvents.filter { p -> events.none { it.type::class.isInstance(p.type) || p.type::class.isInstance(it.type) } }
                .map { it.type.finished() }).flatten()

    val description
        get() = describeChange(null, null, emptyList())

    fun report(prevHour: Hour?) =
        describeChange(prevHour?.temp, prevHour?.weather, prevHour?.events ?: emptyList()).takeUnless(String::isBlank)

    private fun describeChange(prevTemp: Temperature?, prevWeather: Weather?, prevEvents: List<Event>) =
        (listOfNotNull(temp.describeChange(prevTemp)) + weather.describeChanges(prevWeather) + describeEvents(prevEvents))
            .joinToString("\n")

    private val humidMod
        get() = weather.precipitation.type.takeUnless { it is None }?.fireRetardance?.times(0.01F) ?: temp.evaporationMod

    val nextHumidity
        get() = (humidity + humidMod).coerceIn(0F, 1F)
}
