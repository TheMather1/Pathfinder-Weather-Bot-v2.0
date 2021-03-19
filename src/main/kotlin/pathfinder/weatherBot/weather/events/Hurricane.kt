package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.weather.Weather

class Hurricane : Event<Hurricane> {
    override fun progress(weather: Weather): Event<Hurricane>? {
        TODO("not implemented")
    }

    override val finished = "The storm seems to have dissipated some time after being on land."
    override fun description(prev: Hurricane?): String {
        "A cyclone incoming from the ocean can be seen from the beaches of Reloria. Take shelter, it's a hurricane!"
    }
}