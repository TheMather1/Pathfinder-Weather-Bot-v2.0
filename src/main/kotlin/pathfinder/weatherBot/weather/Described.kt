package pathfinder.weatherBot.weather

interface Described<in T> {
    fun print(prev: T?): String?
    val finished: String
}
