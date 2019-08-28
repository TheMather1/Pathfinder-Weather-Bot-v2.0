package weatherBot.weather.events

import weatherBot.time.TimeFrame

interface Event {
    val timeFrame: TimeFrame
    val description: String
    val finished: String
}