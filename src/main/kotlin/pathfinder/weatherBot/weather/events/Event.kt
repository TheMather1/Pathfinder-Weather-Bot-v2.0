package pathfinder.weatherBot.weather.events

import jakarta.persistence.*
import pathfinder.weatherBot.interaction.GuildConfig
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.events.tornado.Tornado
import java.time.LocalDateTime

@Suppress("JpaObjectClassSignatureInspection")
@Entity
class Event(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "EVENT_START")
    val start: LocalDateTime,
    @Column(name = "EVENT_END")
    var end: LocalDateTime,
    @Column(name = "EVENT_TYPE")
    @Suppress("JpaAttributeTypeInspection") //Manually defined JPAConverter
    val type: EventType,
    var active: Boolean = false
) {

    val name: String
        get() = type.name

    fun persist(nextHour: Hour) = when(type) {
        is Wildfire -> type.persist(this, nextHour)
        is Sandstorm -> type.persist(this, nextHour)
        is Tornado -> type.persist(this, nextHour)
        else -> null
    }

    companion object {
        fun persistOrGenerateEvents(config: GuildConfig, prevHour: Hour?, hour: Hour): List<Event> {
            val wildfire = prevHour?.events?.firstOrNull { it.type is Wildfire }?.persist(hour) ?: Wildfire(hour)
            wildfire?.let(hour.events::add) //Wildfires occur first, in case of Firenados.

            val tornado = prevHour?.events?.firstOrNull { it.type is Tornado }?.persist(hour) ?: Tornado(hour)
            val sandstorm = if (config.desert) {
                prevHour?.events?.firstOrNull { it.type is Sandstorm }?.persist(hour)
                    ?: Haboob(hour) ?: Sandstorm(hour)
            } else null
            return listOfNotNull(tornado, sandstorm)
        }
    }
}
