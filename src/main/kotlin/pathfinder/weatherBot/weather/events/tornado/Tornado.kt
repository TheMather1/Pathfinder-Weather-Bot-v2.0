package pathfinder.weatherBot.weather.events.tornado

import pathfinder.weatherBot.d
import pathfinder.weatherBot.time.TimeFrame
import pathfinder.weatherBot.weather.events.Event
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import pathfinder.weatherBot.weather.precipitation.snow.Snow
import java.time.LocalDateTime

open class Tornado(val timeFrame: TimeFrame) : Event {

    companion object {
        operator fun invoke(trigger: Precipitation?): Tornado? {
            return if (trigger is Thunder && trigger.tornado)
                with(
                        TimeFrame(trigger.start, trigger.start.plusHours(3 d 6)),
                        if (trigger is Snow) ::Snownado else ::Tornado)
            else null
        }
    }
    override fun progress(time: LocalDateTime) = takeIf { time in timeFrame }

    override val description
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val finished
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

}