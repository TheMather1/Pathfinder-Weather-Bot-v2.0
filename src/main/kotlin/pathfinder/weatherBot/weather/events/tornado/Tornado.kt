package pathfinder.weatherBot.weather.events.tornado

//open class Tornado(val hours: Long) : Event<Tornado> {
//
//    companion object {
//        operator fun invoke(trigger: Precipitation?): Tornado? {
//            return if (trigger is Thunder && trigger.tornado)
//                with(3 d 6, if (trigger is Snow) ::Snownado else ::Tornado)
//            else null
//        }
//    }
//
//    override fun progress(weather: Weather) = if (hours > 0) this::class.primaryConstructor?.call(hours - 1) else null
//    override val finished = TODO("not implemented")
//
//    override fun description(prev: Tornado?): String {
//        "Thrashing winds from opposing directions stir into a violent spiral. A tornado suddenly forms!"
//    }
//
//}