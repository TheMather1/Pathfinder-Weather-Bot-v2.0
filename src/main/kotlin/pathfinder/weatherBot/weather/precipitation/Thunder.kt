package pathfinder.weatherBot.weather.precipitation

import pathfinder.diceSyntax.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.weather.Temperature
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.Wind.*

object Thunder {

    val wind
        get() = when ((1 d 100).toInt()) {
            in 1..50 -> STRONG
            in 51..90 -> SEVERE
            in 91..100 -> WINDSTORM
            else -> throw dHundredException
        }

    fun tornado(wind: Wind) = wind == WINDSTORM && (1 d 100).toInt() <= 10

    fun hurricane(wind: Wind, temp: Temperature) = wind == WINDSTORM && temp.temp > 85 && (1 d 100).toInt() <= 20

    @Suppress("unused")
    fun lightning() {
        TODO()
    }
}
