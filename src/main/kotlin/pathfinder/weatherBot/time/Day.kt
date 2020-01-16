package pathfinder.weatherBot.time

import java.io.Serializable
import java.time.LocalDate

data class Day(val hours: Array<Hour>, val date: LocalDate): Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Day

        if (!hours.contentEquals(other.hours)) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = hours.contentHashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}