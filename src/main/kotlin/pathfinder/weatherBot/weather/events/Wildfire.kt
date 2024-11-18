package pathfinder.weatherBot.weather.events

import pathfinder.diceSyntax.d
import pathfinder.weatherBot.time.Hour
import java.time.temporal.ChronoUnit.HOURS

open class Wildfire : EventType {
    companion object {
        operator fun invoke(hour: Hour): Event? = if (hour.hotAndDry() || hour.lightningSpark()) Event(
            0, hour.time, hour.time.plusHours(1), Wildfire()
        ) else null

        private fun Hour.hotAndDry() = (1 d 100).toLong() <= fireRisk

        private fun Hour.lightningSpark() = weather.precipitation.type.isThunder && (1 d 100).toLong() >= (humidity * 100) + 60
    }

    fun persist(event: Event, hour: Hour) = event.takeIf {
        (1 d 10).toLong() + hour.fireRisk > it.start.until(hour.time, HOURS)
    }?.also {
        it.end = event.end.plusHours(1)
    }

    override fun describeChange(prev: List<Event>): String? =
        if (prev.any { it.type is Wildfire }) "The wildfire keeps raging."
        else "A wildfire erupts in the distance and quickly swallows the area."

    override fun finished() = "The wildfire is extinguished."
}
