package pathfinder.weatherBot.weather

import pathfinder.weatherBot.location.Climate
import java.io.Serializable

class TempVar(
    private val climate: Climate,
    private val diceFun: () -> Long,
    private val daysLeft: Long
): Serializable {

    operator fun invoke() = diceFun()

    fun next() = if (daysLeft > 0) TempVar(climate, diceFun, daysLeft - 1) else climate.tempVar()
}