package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.d
import pathfinder.weatherBot.time.TimeFrame

class Hail(override val timeFrame: TimeFrame) : Event {
    override val description = TODO()
    override val finished = TODO() //To change initializer of created properties use File | Settings | File Templates.
    private val damaging = (1 d 100) <= 5
}