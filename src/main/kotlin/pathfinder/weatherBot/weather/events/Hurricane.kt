package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.Weather

class Hurricane : EventType<Hurricane> {

    override fun finished() = "The storm seems to have dissipated some time after being on land."

    override fun describeChange(prev: List<Event>): String? {
        "A cyclone incoming from the ocean can be seen from the beaches of Reloria. Take shelter, it's a hurricane!"
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