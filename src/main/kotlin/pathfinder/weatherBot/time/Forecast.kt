package pathfinder.weatherBot.time

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.weather.events.Event

@Suppress("JpaObjectClassSignatureInspection")
@Entity(name = "FORECASTS")
class Forecast(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    var today: Day,
    @OneToOne(cascade = [CascadeType.ALL])
    var tomorrow: Day,
    @OneToOne(cascade = [CascadeType.ALL])
    var dayAfterTomorrow: Day
) {

    val allEvents: List<Event>
        get() = (today.hours.flatMap { it.events } + tomorrow.hours.flatMap {
            it.events
        } + dayAfterTomorrow.hours.flatMap { it.events }).toSet().sortedBy { it.start }

    fun advanceDay(config: GuildConfig) {
        today = tomorrow
        tomorrow = dayAfterTomorrow
        dayAfterTomorrow = tomorrow.nextDay(config)
    }

    companion object {
        fun fromConfig(config: GuildConfig): Forecast {
            val today = Day.fromConfig(config)
            val tomorrow = today.nextDay(config)
            return Forecast(0, today, tomorrow, tomorrow.nextDay(config))
        }
    }
}
