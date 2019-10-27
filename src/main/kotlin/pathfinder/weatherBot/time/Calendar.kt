package pathfinder.weatherBot.time

import pathfinder.weatherBot.PushQueue
import pathfinder.weatherBot.d
import pathfinder.weatherBot.location.Location
import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.precipitation.Precipitation
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.LocalTime

class Calendar(val location: Location) {
    internal val days = pregenDays()
    val precipitation: Precipitation?
        get() = days.peek().precipitation(LocalTime.now())
    val weather: Weather
        get() = days.peek().weather
    private var tempVarEnd = now()
    private var tempVarDie = { 1 d 1 }

    private fun pregenDays(): PushQueue<Day> {
        val queue = PushQueue<Day>(4)
        (-1L..3L).map(now()::plusDays).forEach{
            queue.add(Day(this, it, queue.lastOrNull()?.weather))
        }
        return queue
    }

    fun nextDay() = days.push(Day(this, now().plusDays(3), days.lastOrNull()?.weather))

    internal fun tempVar(day: LocalDate): Long =
            if(day != tempVarEnd) tempVarDie()
            else newTempVar(day)

    private fun newTempVar(day : LocalDate): Long = location.climate.tempVariation().run {
        tempVarDie = first
        tempVarEnd = day.plusDays(second)
        first()
}
}