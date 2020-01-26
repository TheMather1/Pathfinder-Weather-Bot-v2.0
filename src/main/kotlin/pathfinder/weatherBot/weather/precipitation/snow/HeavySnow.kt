package pathfinder.weatherBot.weather.precipitation.snow

import pathfinder.weatherBot.d
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Precipitation


open class HeavySnow(weather: Weather, hours: Long) : Snow(weather, hours) {
    override val fireRetardance = 75

    companion object {
        fun blizzard(wind: Wind): Boolean = wind >= Wind.SEVERE && (1 d 100) <= 40
        operator fun invoke(weather: Weather, hours: Long): HeavySnow {
            val wind = Wind()
            fun blizzardDuration() = if ((1 d 100) <= 20) 2 d 12 else hours
            return when {
                thunder() -> Thundersnow(weather, hours)
                blizzard(wind) -> Blizzard(weather, blizzardDuration())
                else -> HeavySnow(weather, hours)
            }
        }
    }

    override fun fall() {
        weather.hour.day.forecast.biome.snowLevel += (1 d 4)
    }

    override fun description(prev: Precipitation?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val finished: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}