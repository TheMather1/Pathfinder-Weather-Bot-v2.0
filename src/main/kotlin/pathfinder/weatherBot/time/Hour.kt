package pathfinder.weatherBot.time

import jdk.jfr.Percentage
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.events.Event
import pathfinder.weatherBot.weather.events.tornado.Tornado
import java.io.Serializable
import java.time.LocalDateTime

class Hour(config: GuildConfig, val day: Day, val time: LocalDateTime, prevHour: Hour? = null) : Serializable {
    private val prevEvents = prevHour?.events ?: emptyList()
    private val prevTemp = prevHour?.temp
    var temp = day.temperatureRange.tempAtHour(time.toLocalTime())
    val weather: Weather = Weather(config, day.season, time, temp, prevHour?.weather)

    @Percentage
    var humidity: Float = prevHour?.nextHumidity ?: 1F
        set(v) {
            field = v.coerceIn(0F, 1F)
        }

    val fireRisk: Float
        get() = (temp.temp - 100 - (weather.precipitation?.fireRetardance ?: 0)) * (1 - humidity)


    val events: List<Event<*>> = (prevHour?.events?.toMutableList() ?: mutableListOf()).apply {
        if (none { it is Tornado }) Tornado(this@Hour)?.let(::add)
    }

    private val eventDescriptions
        get() = listOfNotNull(events.mapNotNull { e -> e.description(prevEvents) },
            prevEvents.filter { p -> events.none { it::class.isInstance(p) || p::class.isInstance(it) } }
                .map { it.finished }).flatten()

    val description
        get() = (listOfNotNull(temp.describe(prevTemp)) + weather.descriptions + eventDescriptions).joinToString("\n")

    private val humidMod
        get() = weather.precipitation?.fireRetardance?.times(0.01F) ?: temp.evaporationMod

    private val nextHumidity
        get() = (humidity + humidMod).coerceIn(0F, 1F)
}