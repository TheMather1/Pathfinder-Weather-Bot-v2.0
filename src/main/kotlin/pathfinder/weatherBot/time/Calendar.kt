package pathfinder.weatherBot.time

import pathfinder.weatherBot.PushQueue
import pathfinder.weatherBot.d
import pathfinder.weatherBot.location.Location
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.LocalTime
import java.time.temporal.ChronoUnit.HOURS

class Calendar(val location: Location) {
    internal val days = pregenDays()
    val precipitation
        get() = days.peek().precipitation(LocalTime.now().truncatedTo(HOURS))
    val weather
        get() = days.peek().weather
    private var tempVarEnd = now()
    private var tempVarDie = { 1 d 1 }

    private fun pregenDays(): PushQueue<Day> = (0..3).fold(PushQueue(4)) { dayQueue, x ->
        dayQueue.also { it + Day(this, now().plusDays((x - 1).toLong()), dayQueue.lastOrNull()) }
    }

    fun nextDay() = days.push(Day(this, now().plusDays(3), days.lastOrNull()))

    internal fun tempVar(day: LocalDate) = (tempVarDie.takeUnless { day == tempVarEnd }
            ?: location.climate.tempVariation()
                    .apply { tempVarEnd = day.plusDays(second) }
                    .first.apply(::tempVarDie::set)
            )()
}