package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

class Sleet(date: LocalDate, hours: Long) : Snow(date, hours) {
    override fun fall() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()
}