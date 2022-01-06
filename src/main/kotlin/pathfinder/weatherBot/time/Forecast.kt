package pathfinder.weatherBot.time

import pathfinder.weatherBot.interaction.GuildConfig
import java.io.Serializable
import java.time.LocalDate

class Forecast(config: GuildConfig) : Serializable {
    var today: Day = Day(config, LocalDate.now(), null)
        private set
    var tomorrow: Day = today.next(config)
        private set
    var dayAfterTomorrow: Day = tomorrow.next(config)
        private set

    fun progress(config: GuildConfig) {
        today = tomorrow
        tomorrow = dayAfterTomorrow
        dayAfterTomorrow = tomorrow.next(config)
    }

    fun reset(config: GuildConfig) {
        today = Day(config, LocalDate.now(), null)
        tomorrow = today.next(config)
        dayAfterTomorrow = tomorrow.next(config)
    }
}