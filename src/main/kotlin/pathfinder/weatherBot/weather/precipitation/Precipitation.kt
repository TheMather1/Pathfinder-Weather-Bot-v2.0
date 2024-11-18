package pathfinder.weatherBot.weather.precipitation

import pathfinder.weatherBot.weather.Described

interface Precipitation : Described<Precipitation> {
    @Suppress("EmptyMethod") //TODO
    fun fall()

    val fireRetardance: Int
    @Suppress("SameReturnValue") //Overriden
    val tempAdjust: Int
        get() = 0

    @Suppress("SameReturnValue") //Overriden
    val isThunder: Boolean
        get() = false

    @Suppress("SameReturnValue") //Overriden
    val warn: String?
        get() = null

    @Suppress("SameReturnValue") //Overriden
    val iconUrl: String?
        get() = null
}