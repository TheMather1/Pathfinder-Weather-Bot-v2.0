package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.weather.Weather

open class Wildfire(private val weather: Weather) : Event<Wildfire> {
    companion object {
        operator fun invoke(weather: Weather): Wildfire? {
            return null
        //weather.hour.day.forecast.biome.humidity.let {
//                if (
//                    (weather.hour.temp - 90 - it - (weather.precipitation?.fireRetardance ?: 0)) / 2 <= 100
//                    || (weather.precipitation is Thunderstorm && (1 d 100) <= 60 - it)
//                ) Wildfire(weather) else null
//            }
        }
    }

    override fun progress(weather: Weather): Wildfire? =
        if (TODO("extinguish?")) null else Wildfire(weather)

    override fun description(prev: Wildfire?): String = "A fire has started in the wilderness and seems to be growing out of control!"
    override val finished = "The wildfire is extinguished."
}