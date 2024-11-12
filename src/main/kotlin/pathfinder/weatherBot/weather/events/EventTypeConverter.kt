package pathfinder.weatherBot.weather.events

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import org.slf4j.LoggerFactory
import pathfinder.weatherBot.weather.DiceConverter
import kotlin.reflect.full.createInstance

@Converter(autoApply = true)
class EventTypeConverter: AttributeConverter<EventType<*>, String> {
    val logger = LoggerFactory.getLogger(DiceConverter::class.java)

    override fun convertToDatabaseColumn(attribute: EventType<*>): String? {
        return attribute::class.qualifiedName
    }

    override fun convertToEntityAttribute(dbData: String): EventType<*>? {
        return try {
            if (dbData.isBlank()) null else Class.forName(dbData).kotlin.createInstance() as EventType<*>
        } catch (e: Throwable) {
            logger.error("Error parsing event type '$dbData'.", e)
            null
        }
    }
}
