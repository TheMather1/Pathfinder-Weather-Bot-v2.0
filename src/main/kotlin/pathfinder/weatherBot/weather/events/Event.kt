package pathfinder.weatherBot.weather.events

import pathfinder.weatherBot.time.TimeFrame

interface Event {
    val timeFrame: TimeFrame
    val description: String
    val finished: String
}