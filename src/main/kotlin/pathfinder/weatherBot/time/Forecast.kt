package pathfinder.weatherBot.time

import pathfinder.weatherBot.location.Biome
import java.io.Serializable

data class Forecast(private val biome: Biome): Serializable {
    var dayAfterTomorrow: Day = TODO()
        private set
    var tomorrow: Day = TODO()
        private set
    var today: Day = TODO()
        private set
}