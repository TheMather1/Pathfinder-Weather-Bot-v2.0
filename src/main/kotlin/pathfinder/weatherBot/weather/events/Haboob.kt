package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.weather.Weather

class Haboob : Sandstorm() {
    override fun progress(weather: Weather): Event<Sandstorm>? {
        TODO("not implemented")
    }
}