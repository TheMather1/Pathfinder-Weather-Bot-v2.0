package pathfinder.weatherBot

class PushQueue<T>(private val maxSize: Int) {

    private var container: ArrayList<T> = arrayListOf()
    val size
        get() = container.size

    fun push(element: T): T = container.apply{ add(element) }.removeAt(0)

    fun add(element: T): Boolean = size < maxSize && container.add(element)

    fun peek(): T = container.first()

    fun lastOrNull(): T? = container.lastOrNull()
}