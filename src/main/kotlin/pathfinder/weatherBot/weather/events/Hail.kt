package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.d
import java.time.LocalDateTime

class Hail : Event {
    override fun progress(time: LocalDateTime): Event? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val description = TODO()
    override val finished = TODO()
    private val damaging = (1 d 100) <= 5
}