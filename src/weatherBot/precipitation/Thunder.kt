package weatherBot.precipitation

import weatherBot.Wind
import weatherBot.d
import weatherBot.dHundredException

interface Thunder {
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