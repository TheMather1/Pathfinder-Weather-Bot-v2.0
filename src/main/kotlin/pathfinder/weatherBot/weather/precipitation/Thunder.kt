package pathfinder.weatherBot.weather.precipitation

import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.location.Location

interface Thunder: Precipitation {
    companion object {
        val tornado: Boolean
            get() = wind == Wind.WINDSTORM && (1 d 100) <= 10
        val haboob: Boolean
            get() = Location.desert && (1 d 100) <= 20
        val wind: Wind
            get(): Wind = when (1 d 100) {
                in 1..50 -> Wind.STRONG
                in 51..90 -> Wind.SEVERE
                in 91..100 -> Wind.WINDSTORM
                else -> throw dHundredException
            }
    }
    val temp: Long
    val hurricane: Boolean
        get() = temp > 85 && (1 d 100) <= 20

    val wind: Wind
        get() = Thunder.wind
    fun lightning(){

    }
}