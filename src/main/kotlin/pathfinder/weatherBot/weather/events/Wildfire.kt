package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.d
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.rain.Thunderstorm

open class Wildfire(private val weather: Weather) : Event<Wildfire> {
    companion object {
        operator fun invoke(weather: Weather): Wildfire? {
            return weather.hour.day.forecast.biome.humidity.let {
                if (
                    (weather.hour.temp - 90 - it - (weather.precipitation?.fireRetardance ?: 0)) / 2 <= 100
                    || (weather.precipitation is Thunderstorm && (1 d 100) <= 60 - it)
                ) Wildfire(weather) else null
            }
        }
    }

    override fun progress(weather: Weather): Wildfire? =
        if (TODO("extinguish?")) null else Wildfire(weather)

    override fun description(prev: Wildfire?): String = TODO()
    override val finished = TODO()
}