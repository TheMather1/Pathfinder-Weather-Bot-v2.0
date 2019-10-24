package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate

class LightSnow(val location: Location, date: LocalDate, hours: Long) : Snow(date, hours) {
    override fun print(prev: Precipitation?): String = TODO()

    override fun finished(): String = TODO()

    override fun fall() {
        location.snowLevel += 0.5
    }
}