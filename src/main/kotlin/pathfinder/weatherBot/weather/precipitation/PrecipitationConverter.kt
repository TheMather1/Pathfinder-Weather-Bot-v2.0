package pathfinder.weatherBot.weather.precipitation

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class PrecipitationConverter: AttributeConverter<Precipitation, String> {
    val entries: List<Precipitation> = Rain.entries + Fog.entries + Snow.entries + None.entries
    override fun convertToDatabaseColumn(attribute: Precipitation): String {
        return (attribute as Enum<*>).name
    }

    override fun convertToEntityAttribute(dbData: String): Precipitation {
        return entries.first { it.toString() == dbData }
    }

}