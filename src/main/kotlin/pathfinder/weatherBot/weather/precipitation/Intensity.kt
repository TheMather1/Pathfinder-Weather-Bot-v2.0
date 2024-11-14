package pathfinder.weatherBot.weather.precipitation

import pathfinder.diceSyntax.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.weather.Temperature
import pathfinder.weatherBot.weather.Wind
import pathfinder.weatherBot.weather.precipitation.Fog.*
import pathfinder.weatherBot.weather.precipitation.Rain.*
import pathfinder.weatherBot.weather.precipitation.Snow.*
import java.time.LocalDateTime
import kotlin.math.max
import kotlin.math.min

enum class Intensity {
    LIGHT{
         override fun wet(start: LocalDateTime, temp: Temperature) = when((1 d 100).toLong()){
             in 1..20 -> PrecipitationWrapper(start, start.plusHours((1 d 8).toLong()), LIGHT_FOG)
             in 21..40 -> PrecipitationWrapper(start, start.plusHours((1 d 6).toLong()), MEDIUM_FOG)
             in 41..50 -> PrecipitationWrapper(start, start.plusHours((1 d 4).toLong()), DRIZZLE)
             in 51..75 -> PrecipitationWrapper(start, start.plusHours((2 d 12).toLong()), DRIZZLE)
             in 76..90 -> PrecipitationWrapper(start, start.plusHours((1 d 4).toLong()), LIGHT_RAIN)
             in 91..100 -> if (temp.cold) PrecipitationWrapper(start, start.plusHours(1), SLEET) else PrecipitationWrapper(start, start.plusHours(1), LIGHT_RAIN)
             else -> throw dHundredException
         }

        override fun frozen(start: LocalDateTime) = when((1 d 100).toLong()){
            in 1..20 -> PrecipitationWrapper(start, start.plusHours((1 d 6).toLong()), LIGHT_FOG)
            in 21..40 -> PrecipitationWrapper(start, start.plusHours((1 d 8).toLong()), LIGHT_FOG)
            in 41..50 -> PrecipitationWrapper(start, start.plusHours((1 d 4).toLong()), MEDIUM_FOG)
            in 51..60 -> PrecipitationWrapper(start, start.plusHours(1), LIGHT_SNOW)
            in 61..75 -> PrecipitationWrapper(start, start.plusHours((1 d 4).toLong()), LIGHT_SNOW)
            in 76..100 -> PrecipitationWrapper(start, start.plusHours((2 d 12).toLong()), LIGHT_SNOW)
            else -> throw dHundredException
        }
    },
    MEDIUM {
        override fun wet(start: LocalDateTime, temp: Temperature) = when((1 d 100).toLong()){
            in 1..10 -> PrecipitationWrapper(start, start.plusHours((1 d 8).toLong()), MEDIUM_FOG)
            in 11..20 -> PrecipitationWrapper(start, start.plusHours((1 d 12).toLong()), MEDIUM_FOG)
            in 21..30 -> PrecipitationWrapper(start, start.plusHours((1 d 4).toLong()), HEAVY_FOG)
            in 31..35 -> PrecipitationWrapper(start, start.plusHours((1 d 4).toLong()), MEDIUM_RAIN)
            in 36..70 -> PrecipitationWrapper(start, start.plusHours((1 d 8).toLong()), MEDIUM_RAIN)
            in 71..90 -> PrecipitationWrapper(start, start.plusHours((2 d 12).toLong()), MEDIUM_RAIN)
            in 91..100 -> if (temp.cold) PrecipitationWrapper(start, start.plusHours((1 d 4).toLong()), SLEET) else PrecipitationWrapper(start, start.plusHours((1 d 4).toLong()), MEDIUM_RAIN)
            else -> throw dHundredException
        }

        override fun frozen(start: LocalDateTime) = when((1 d 100).toLong()){
            in 1..10 -> PrecipitationWrapper(start, start.plusHours((1 d 8).toLong()), MEDIUM_FOG)
            in 11..20 -> PrecipitationWrapper(start, start.plusHours((1 d 12).toLong()), MEDIUM_FOG)
            in 21..30 -> PrecipitationWrapper(start, start.plusHours((1 d 4).toLong()), HEAVY_FOG)
            in 31..50 -> PrecipitationWrapper(start, start.plusHours((1 d 4).toLong()), MEDIUM_SNOW)
            in 51..90 -> PrecipitationWrapper(start, start.plusHours((1 d 8).toLong()), MEDIUM_SNOW)
            in 91..100 -> PrecipitationWrapper(start, start.plusHours((2 d 12).toLong()), LIGHT_SNOW)
            else -> throw dHundredException
        }
    },
    HEAVY {
        override fun wet(start: LocalDateTime, temp: Temperature) = when ((1 d 100).toLong()) {
            in 1..10 -> PrecipitationWrapper(start, start.plusHours((1 d 8).toLong()), HEAVY_FOG)
            in 11..20 -> PrecipitationWrapper(start, start.plusHours((2 d 6).toLong()), HEAVY_FOG)
            in 21..50 -> PrecipitationWrapper(start, start.plusHours((1 d 12).toLong()), HEAVY_RAIN)
            in 51..70 -> PrecipitationWrapper(start, start.plusHours((2 d 12).toLong()), HEAVY_RAIN)
            in 71..85 -> if (temp.cold) PrecipitationWrapper(start, start.plusHours((1 d 8).toLong()), SLEET) else PrecipitationWrapper(start, start.plusHours((1 d 8).toLong()), HEAVY_RAIN)
            in 86..90 -> PrecipitationWrapper(start, start.plusHours(1), THUNDERSTORM)
            in 91..100 -> PrecipitationWrapper(start, start.plusHours((1 d 3).toLong()), THUNDERSTORM)
            else -> throw dHundredException
        }

        override fun frozen(start: LocalDateTime) = when ((1 d 100).toLong()) {
            in 1..10 -> PrecipitationWrapper(start, start.plusHours((1 d 8).toLong()), HEAVY_FOG)
            in 11..20 -> PrecipitationWrapper(start, start.plusHours((2 d 6).toLong()), HEAVY_FOG)
            in 21..60 -> PrecipitationWrapper(start, start.plusHours((2 d 12).toLong()), LIGHT_SNOW)
            in 61..90 -> PrecipitationWrapper(start, start.plusHours((1 d 8).toLong()), MEDIUM_SNOW)
            in 91..100 -> heavySnow(start, start.plusHours((1 d 6).toLong()))
            else -> throw dHundredException
        }
    };

    operator fun inc() = plus(1)
    operator fun dec() = minus(1)
    operator fun plus(i: Int) = Intensity.entries[min(ordinal + i, Intensity.entries.lastIndex)]
    operator fun minus(i: Int) = Intensity.entries[max(ordinal - i, 0)]
    abstract fun wet(start: LocalDateTime, temp: Temperature): PrecipitationWrapper
    abstract fun frozen(start: LocalDateTime): PrecipitationWrapper


    fun thunder(): Boolean = (1 d 100).toInt() <= 10
    fun blizzard(wind: Wind): Boolean = wind >= Wind.SEVERE && (1 d 100).toInt() <= 40
    fun blizzardDuration(start: LocalDateTime) = if ((1 d 100).toInt() <= 20) start.plusHours((2 d 12).toLong()) else null

    fun heavySnow(start: LocalDateTime, end: LocalDateTime): PrecipitationWrapper {
        val wind = Wind()
        return when {
            thunder() -> thundersnow(start, end)
            blizzard(wind) -> PrecipitationWrapper(start, blizzardDuration(start) ?: end, BLIZZARD, wind)
            else -> PrecipitationWrapper(start, end, HEAVY_SNOW, wind)
        }
    }
    fun thundersnow(start: LocalDateTime, end: LocalDateTime): PrecipitationWrapper = Thunder.wind.let {
        if (blizzard(it)) PrecipitationWrapper(start, end, THUNDER_BLIZZARD, it)
        else PrecipitationWrapper(start, end, THUNDERSNOW, it)
    }
}