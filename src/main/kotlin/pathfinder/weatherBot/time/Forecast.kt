package pathfinder.weatherBot.time

import pathfinder.weatherBot.location.Biome
import java.io.Serializable
import java.time.LocalDate

data class Forecast(val biome: Biome) : Serializable {
    var today: Day = Day(this, LocalDate.now())
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

    fun reset() {
        today = Day(this, LocalDate.now())
        tomorrow = today.next()
        dayAfterTomorrow = tomorrow.next()
    }
}