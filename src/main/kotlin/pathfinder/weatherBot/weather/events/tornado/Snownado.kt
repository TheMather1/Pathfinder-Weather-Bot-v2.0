package pathfinder.weatherBot.weather.events.tornado

import pathfinder.weatherBot.weather.events.Event

class Snownado : Tornado() {
    override fun finished() = "The snownado dissipates, leaving behind snow littered with debris."

    override fun describeChange(prev: List<Event>) = when(prev.firstOrNull { it.type is Tornado }?.type) {
        null -> "Thrashing winds and falling snow combine into a terrifying snownado."
        is Snownado -> null
        is Firenado -> "The fires are quashed by snow, turning the firenado into a snownado."
        else -> "The tornado sucks up the falling snow, becoming a snownado."
    }
}