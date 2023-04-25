package pathfinder.weatherBot.weather

interface Described<in T> {
    fun describeChange(prev: T?): String?
    val finished: String
}
