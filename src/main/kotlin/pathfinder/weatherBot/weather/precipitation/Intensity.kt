package pathfinder.weatherBot.weather.precipitation

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.weather.Temperature
import pathfinder.weatherBot.weather.precipitation.fog.HeavyFog
import pathfinder.weatherBot.weather.precipitation.fog.LightFog
import pathfinder.weatherBot.weather.precipitation.fog.MediumFog
import pathfinder.weatherBot.weather.precipitation.rain.*
import pathfinder.weatherBot.weather.precipitation.snow.HeavySnow
import pathfinder.weatherBot.weather.precipitation.snow.LightSnow
import pathfinder.weatherBot.weather.precipitation.snow.MediumSnow
import pathfinder.weatherBot.weather.precipitation.snow.Sleet
import java.time.LocalDateTime
import kotlin.math.max
import kotlin.math.min

enum class Intensity {
    LIGHT{
         override fun wet(start: LocalDateTime, temp: Temperature) = when(1 d 100){
             in 1..20 -> LightFog(start, start.plusHours(1 d 8))
             in 21..40 -> MediumFog(start, start.plusHours(1 d 6))
             in 41..50 -> Drizzle(start, start.plusHours(1 d 4))
             in 51..75 -> Drizzle(start, start.plusHours(2 d 12))
             in 76..90 -> LightRain(start, start.plusHours(1 d 4))
             in 91..100 -> if (temp.cold) Sleet(start, start.plusHours(1)) else LightRain(start, start.plusHours(1))
             else -> throw dHundredException
         }

        override fun frozen(start: LocalDateTime) = when(1 d 100){
            in 1..20 -> LightFog(start, start.plusHours(1 d 6))
            in 21..40 -> LightFog(start, start.plusHours(1 d 8))
            in 41..50 -> MediumFog(start, start.plusHours(1 d 4))
            in 51..60 -> LightSnow(start, start.plusHours(1))
            in 61..75 -> LightSnow(start, start.plusHours(1 d 4))
            in 76..100 -> LightSnow(start, start.plusHours(2 d 12))
            else -> throw dHundredException
        }
    },
    MEDIUM {
        override fun wet(start: LocalDateTime, temp: Temperature) = when(1 d 100){
            in 1..10 -> MediumFog(start, start.plusHours( 1 d 8))
            in 11..20 -> MediumFog(start, start.plusHours( 1 d 12))
            in 21..30 -> HeavyFog(start, start.plusHours(1 d 4))
            in 31..35 -> MediumRain(start, start.plusHours( 1 d 4))
            in 36..70 -> MediumRain(start, start.plusHours( 1 d 8))
            in 71..90 -> MediumRain(start, start.plusHours(2 d 12))
            in 91..100 -> if (temp.cold) Sleet(start, start.plusHours(1 d 4)) else MediumRain(start, start.plusHours(1 d 4))
            else -> throw dHundredException
        }

        override fun frozen(start: LocalDateTime) = when(1 d 100){
            in 1..10 -> MediumFog(start, start.plusHours(1 d 8))
            in 11..20 -> MediumFog(start, start.plusHours(1 d 12))
            in 21..30 -> HeavyFog(start, start.plusHours(1 d 4))
            in 31..50 -> MediumSnow(start, start.plusHours(1 d 4))
            in 51..90 -> MediumSnow(start, start.plusHours(1 d 8))
            in 91..100 -> LightSnow(start, start.plusHours(2 d 12))
            else -> throw dHundredException
        }
    },
    HEAVY {
        override fun wet(start: LocalDateTime, temp: Temperature) = when (1 d 100) {
            in 1..10 -> HeavyFog(start, start.plusHours(1 d 8))
            in 11..20 -> HeavyFog(start, start.plusHours(2 d 6))
            in 21..50 -> HeavyRain(start, start.plusHours(1 d 12))
            in 51..70 -> HeavyRain(start, start.plusHours(2 d 12))
            in 71..85 -> if (temp.cold) Sleet(start, start.plusHours(1 d 8)) else HeavyRain(start, start.plusHours(1 d 8))
            in 86..90 -> Thunderstorm(start, start.plusHours(1))
            in 91..100 -> Thunderstorm(start, start.plusHours(1 d 3))
            else -> throw dHundredException
        }

        override fun frozen(start: LocalDateTime) = when (1 d 100) {
            in 1..10 -> MediumFog(start, start.plusHours(1 d 8))
            in 11..20 -> HeavyFog(start, start.plusHours(2 d 6))
            in 21..60 -> LightSnow(start, start.plusHours(2 d 12))
            in 61..90 -> MediumSnow(start, start.plusHours(1 d 8))
            in 91..100 -> HeavySnow(start, start.plusHours(1 d 6))
            else -> throw dHundredException
        }
    };

    operator fun inc() = plus(1)
    operator fun dec() = minus(1)
    operator fun plus(i: Int) = values()[min(ordinal + i, values().lastIndex)]
    operator fun minus(i: Int) = values()[max(ordinal - i, 0)]
    abstract fun wet(start: LocalDateTime, temp: Temperature): Precipitation
    abstract fun frozen(start: LocalDateTime): Precipitation
}