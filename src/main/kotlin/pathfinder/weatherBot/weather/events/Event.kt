package pathfinder.weatherBot.weather.events

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Event(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "EVENT_START")
    val start: LocalDateTime,
    @Column(name = "EVENT_END")
    var end: LocalDateTime,
    @Column(name = "EVENT_TYPE")
    val type: EventType<*>,
    var active: Boolean = false
) {

    val name: String
        get() = type.name
}
