package pathfinder.weatherBot.weather.events

import pathfinder.diceSyntax.d
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Weather

class Hail : EventType<Hail> {

    private val damaging = (1 d 100).toInt() <= 5

    override fun finished(): String = "The hail stops, being outside no longer hurts!"

    override fun describeChange(prev: List<Event>): String? {
        "Small pebbles of ice fall from the sky, pelting the ground and whatever else happens to be exposed."
        TODO("Not yet implemented")
    }

    override fun progress(
        hour: Hour,
        weather: Weather,
        event: Event
    ): Event? {
        TODO("Not yet implemented")
    }
}