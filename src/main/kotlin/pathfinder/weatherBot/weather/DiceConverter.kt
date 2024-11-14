package pathfinder.weatherBot.weather

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import pathfinder.diceSyntax.DiceParser
import pathfinder.diceSyntax.components.DiceComponent

@Converter(autoApply = true)
class DiceConverter: AttributeConverter<DiceComponent<*,*,*>, String> {
    val logger: Logger = LoggerFactory.getLogger(DiceConverter::class.java)

    override fun convertToDatabaseColumn(attribute: DiceComponent<*,*,*>): String {
        return attribute.toString()
    }

    override fun convertToEntityAttribute(dbData: String): DiceComponent<*,*,*>? {
        return try {
            if (dbData.isBlank()) null else DiceParser().parse(dbData)
        } catch (e: Throwable) {
            logger.error("Error parsing dice function '$dbData'.", e)
            null
        }
    }

}