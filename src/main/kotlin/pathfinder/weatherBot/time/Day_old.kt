package pathfinder.weatherBot.time

import pathfinder.weatherBot.weather.Weather
import pathfinder.weatherBot.weather.events.Event
import java.time.LocalDate
import java.time.LocalTime

class Day_old(calendar: Calendar, val day: LocalDate, prevDay: Day_old?) {
    val weather: Weather = Weather(calendar.location, Season(day), calendar.tempVar(day), day, prevDay?.weather)
    val events = (0..23).map { day.atTime(it, 0) }.fold(ArrayList<List<Event>>()) { eventList, time ->
        eventList.apply { add(Event(eventList.lastOrNull() ?: emptyList(), time, weather, calendar.location)) }
    }

    fun precipitation(now: LocalTime) = weather.precipitation(now)
}