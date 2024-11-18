package pathfinder.weatherBot.weather.events

import pathfinder.diceSyntax.d

@Suppress("unused")
class Hail : EventType {

    private val damaging = (1 d 100).toInt() <= 5

    override fun finished(): String = "The hail stops, being outside no longer hurts!"

    override fun describeChange(prev: List<Event>): String? {
        "Small pebbles of ice fall from the sky, pelting the ground and whatever else happens to be exposed."
        TODO("Not yet implemented")
    }

}