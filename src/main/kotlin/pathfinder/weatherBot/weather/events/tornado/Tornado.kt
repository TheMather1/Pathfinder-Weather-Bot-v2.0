package pathfinder.weatherBot.weather.events.tornado

import pathfinder.weatherBot.d
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.events.Event
import pathfinder.weatherBot.weather.precipitation.Precipitation
import pathfinder.weatherBot.weather.precipitation.Thunder
import pathfinder.weatherBot.weather.precipitation.snow.Snow
import kotlin.reflect.full.primaryConstructor

open class Tornado(val hours: Long) : Event<Tornado> {

    companion object {
        operator fun invoke(trigger: Precipitation?): Tornado? {
            return if (trigger is Thunder && trigger.tornado)
                with(3 d 6, if (trigger is Snow) ::Snownado else ::Tornado)
            else null
        }
    }
    override fun progress(weather: Weather) = if (hours > 0) this::class.primaryConstructor?.call(hours-1) else null
    override val finished
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun description(prev: Tornado?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}