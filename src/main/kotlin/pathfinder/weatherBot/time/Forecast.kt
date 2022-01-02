package pathfinder.weatherBot.time

import pathfinder.weatherBot.interaction.GuildConfig
import java.io.Serializable
import java.time.LocalDate

class Forecast(config: GuildConfig) : Serializable {
    var today: Day = Day(config, LocalDate.now())
        private set
    var tomorrow: Day = today.next()
        private set
    var dayAfterTomorrow: Day = tomorrow.next()
        private set

    fun progress() {
        today = tomorrow
        tomorrow = dayAfterTomorrow
        dayAfterTomorrow = tomorrow.next()
    }

    fun reset(config: GuildConfig) {
        today = Day(config, LocalDate.now())
        tomorrow = today.next()
        dayAfterTomorrow = tomorrow.next()
    }
}