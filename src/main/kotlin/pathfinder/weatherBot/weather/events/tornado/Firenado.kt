package pathfinder.weatherBot.weather.events.tornado

import pathfinder.weatherBot.weather.events.Event

class Firenado : Tornado() {
    override fun finished() = "The firenado peters out and disappears, leaving only smoldering debris behind."

    override fun describeChange(prev: List<Event>) = when(prev.firstOrNull { it.type is Tornado }?.type) {
        null -> "Turbulent winds suck up the wildfire, combining into a terrifying firenado."
        is Firenado -> null
        is Snownado -> "The fires are quashed by snow, turning the firenado into a snownado."
        else -> "The wildfire is sucked up by the tornado, combining into a terrifying firenado."
    }
}