package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder

class Thundersnow(weather: Weather, hours: Long, override val wind: Wind) : HeavySnow(weather, hours), Thunder {
    companion object {
        operator fun invoke(weather: Weather, hours: Long): HeavySnow = Thunder.wind.let {
            if (blizzard(it)) ThunderBlizzard(weather, hours, it)
            else Thundersnow(weather, hours, it)
        }
    }

    override fun description(prev: Precipitation?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val finished: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}