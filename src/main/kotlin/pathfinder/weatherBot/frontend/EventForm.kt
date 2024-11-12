package pathfinder.weatherBot.frontend

import pathfinder.weatherBot.weather.events.Event
import pathfinder.weatherBot.weather.events.EventType
import java.time.LocalDateTime.parse

class EventForm(events: List<Event> = emptyList()) {
    val events = events.map {
        FormEvent(it)
    }

    class FormEvent() {
        lateinit var name: String
        lateinit var start: String
        lateinit var end: String
        var active: Boolean = false

        constructor(event: Event) : this() {
            this.name = event.name
            this.start = event.start.toString()
            this.end = event.end.toString()
            this.active = event.active
        }

        fun isEvent(event: Event) = name == event.name && parse(start) == event.start && parse(end) == event.end
    }
}
