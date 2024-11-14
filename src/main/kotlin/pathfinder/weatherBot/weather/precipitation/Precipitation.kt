package pathfinder.weatherBot.weather.precipitation

import pathfinder.weatherBot.weather.Described

interface Precipitation : Described<Precipitation> {
    fun fall()

    val fireRetardance: Int
    val tempAdjust: Int
        get() = 0

    val isThunder: Boolean
        get() = false

    val warn: String?
        get() = null

    val iconUrl: String?
        get() = null
}