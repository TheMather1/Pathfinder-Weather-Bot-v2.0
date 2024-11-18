package pathfinder.weatherBot.weather.events

interface EventType {
    val name: String
        get() = this::class.simpleName!!

    @Suppress("SameReturnValue") //To be overriden
    val description: String?
        get() = "TODO"

    @Suppress("SameReturnValue") //Overriden
    val warn: String?
        get() = null

    fun finished(): String

    fun describeChange(prev: List<Event>): String?
}
