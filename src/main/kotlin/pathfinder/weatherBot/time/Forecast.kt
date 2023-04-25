package pathfinder.weatherBot.time

import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.weather.events.Event
import java.io.Serializable
import java.time.LocalDate

class Forecast(config: GuildConfig) : Serializable {
    var today: Day = Day(config, LocalDate.now(), null)
        private set
    var tomorrow: Day = today.nextDay(config)
        private set
    var dayAfterTomorrow: Day = tomorrow.nextDay(config)
        private set

    val allEvents: List<Event<*>>
        get() = (today.hours.flatMap { it?.events ?: emptyList() } + tomorrow.hours.flatMap {
            it?.events ?: emptyList()
        } + dayAfterTomorrow.hours.flatMap { it?.events ?: emptyList() }).toSet().sortedBy { it.start }

    fun advanceDay(config: GuildConfig) {
        today = tomorrow
        tomorrow = dayAfterTomorrow
        dayAfterTomorrow = tomorrow.nextDay(config)
    }

    fun reset(config: GuildConfig) {
        today = Day(config, LocalDate.now(), null)
        tomorrow = today.nextDay(config)
        dayAfterTomorrow = tomorrow.nextDay(config)
    }
}
