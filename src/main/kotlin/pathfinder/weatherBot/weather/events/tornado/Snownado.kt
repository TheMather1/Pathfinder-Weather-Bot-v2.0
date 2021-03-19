package pathfinder.weatherBot.weather.events.tornado

class Snownado(hours: Long) : Tornado(hours) {
    override val finished = "The tornado dissipates, leaving behind a nice heap of debris for us to remember it by."

    override fun description(prev: Tornado?): String {
       "Winds pick up and begin kicking a slurry of snow in a vortex. It's a snownado!"
    }
}