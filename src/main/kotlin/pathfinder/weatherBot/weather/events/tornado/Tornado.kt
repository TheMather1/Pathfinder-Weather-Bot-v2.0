package pathfinder.weatherBot.weather.events.tornado

import pathfinder.weatherBot.d
import pathfinder.weatherBot.time.TimeFrame
import pathfinder.weatherBot.weather.events.Event
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import pathfinder.weatherBot.weather.precipitation.snow.Snow

open class Tornado(override val timeFrame: TimeFrame) : Event {
    companion object {
        operator fun invoke(trigger: Precipitation): Tornado? {
            return if (trigger is Thunder && trigger.tornado)
                TimeFrame(trigger.start, trigger.start.plusHours(3 d 6)).let {
                    when (trigger) {
                    is Snow -> Snownado(it)
                    else -> Tornado(it)
                }
            }
            else null
        }
    }
    override val description: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val finished: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

}