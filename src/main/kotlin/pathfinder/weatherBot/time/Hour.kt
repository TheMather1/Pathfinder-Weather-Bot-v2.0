package pathfinder.weatherBot.time

import jdk.jfr.Percentage
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.weather.Temperature
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.events.Event
import pathfinder.weatherBot.weather.events.tornado.Tornado
import pathfinder.weatherBot.weather.precipitation.None
import java.io.Serializable
import java.time.LocalDateTime

class Hour(config: GuildConfig, day: Day, val time: LocalDateTime, prevHour: Hour? = null) : Serializable {
    private val prevEvents = prevHour?.events ?: emptyList()
    private val prevTemp = prevHour?.temp
    private val prevWeather = prevHour?.weather
    var temp = day.temperatureRange.tempAtHour(time.toLocalTime())
    val weather: Weather = Weather(config, day.season, time, temp, prevHour?.weather)

    @Percentage
    var humidity: Float = prevHour?.nextHumidity ?: 1F
        set(v) {
            field = v.coerceIn(0F, 1F)
        }

    val fireRisk: Float
        get() = (temp.temp - 100 - (weather.precipitation.fireRetardance)) * (1 - humidity)


    val events: MutableList<Event<*>> = (prevHour?.events?.filter { it.end > time }?.toMutableList() ?: mutableListOf()).apply {
        if (none { it is Tornado }) Tornado(this@Hour)?.let(::add)
    }

    private fun describeEvents(prevEvents: List<Event<*>>) =
        listOfNotNull(events.filter { it.active }.mapNotNull { e -> e.describeChange(prevEvents) },
            prevEvents.filter { p -> events.none { it::class.isInstance(p) || p::class.isInstance(it) } }
                .map { it.finished }).flatten()

    val description
        get() = describeChange(null, null, emptyList())

    val report
        get() = describeChange(prevTemp, prevWeather, prevEvents).takeUnless(String::isBlank)

    private fun describeChange(prevTemp: Temperature?, prevWeather: Weather?, prevEvents: List<Event<*>>) =
        (listOfNotNull(temp.describeChange(prevTemp)) + weather.describeChanges(prevWeather) + describeEvents(prevEvents))
            .joinToString("\n")

    private val humidMod
        get() = weather.precipitation.takeUnless { it is None }?.fireRetardance?.times(0.01F) ?: temp.evaporationMod

    private val nextHumidity
        get() = (humidity + humidMod).coerceIn(0F, 1F)
}
