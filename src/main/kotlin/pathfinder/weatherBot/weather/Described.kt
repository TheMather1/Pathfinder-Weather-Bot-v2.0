package pathfinder.weatherBot.weather

interface Described<in T> {
    fun description(prev: T?): String?
    val finished: String
}