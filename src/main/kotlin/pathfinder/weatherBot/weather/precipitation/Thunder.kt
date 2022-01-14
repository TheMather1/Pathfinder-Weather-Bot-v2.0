package pathfinder.weatherBot.weather.precipitation

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.Wind.*

interface Thunder {
    companion object {
        val wind
            get() = when (1 d 100) {
                in 1..50 -> STRONG
                in 51..90 -> SEVERE
                in 91..100 -> WINDSTORM
                else -> throw dHundredException
            }

        fun tornado(wind: Wind) = wind == WINDSTORM && (1 d 100) <= 10
    }

    fun haboob(config: GuildConfig) = config.desert && (1 d 100) <= 20

    fun hurricane(hour: Hour) = hour.temp.temp > 85 && (1 d 100) <= 20

    fun lightning() {
        TODO()
    }
}
