package weatherBot.weather.precipitation

import weatherBot.weather.Wind
import weatherBot.d
import weatherBot.dHundredException
import weatherBot.location.Location

interface Thunder: Precipitation {
    val temp: Long
    val tornado: Boolean
        get() = wind == Wind.WINDSTORM && (1 d 100) <= 10
    val hurricane: Boolean
        get() = temp > 85 && (1 d 100) <= 20
    val haboob: Boolean
        get() = Location.desert && (1 d 100) <= 20
    val wind: Wind
        get() = when (1 d 100){
            in 1..50 -> Wind.STRONG
            in 51..90 -> Wind.SEVERE
            in 91..100 -> Wind.WINDSTORM
            else -> throw dHundredException
        }

    fun lightning(){

    }
}