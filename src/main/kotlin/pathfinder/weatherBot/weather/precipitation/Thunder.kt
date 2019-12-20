package pathfinder.weatherBot.weather.precipitation

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.location.Location
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
    }
    val location: Location
    val temp: Long

    val tornado
        get() = wind == WINDSTORM && (1 d 100) <= 10

    val haboob
        get() = location.desert && (1 d 100) <= 20

    val hurricane
        get() = temp > 85 && (1 d 100) <= 20

    val wind
        get() = Thunder.wind
    fun lightning(){
        TODO()
    }
}