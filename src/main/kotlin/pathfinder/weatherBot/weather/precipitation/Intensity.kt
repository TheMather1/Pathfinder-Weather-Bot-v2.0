package pathfinder.weatherBot.weather.precipitation

import pathfinder.weatherBot.d
import pathfinder.weatherBot.dHundredException
import pathfinder.weatherBot.time.Hour
import pathfinder.weatherBot.weather.precipitation.fog.HeavyFog
import pathfinder.weatherBot.weather.precipitation.fog.LightFog
import pathfinder.weatherBot.weather.precipitation.fog.MediumFog
import pathfinder.weatherBot.weather.precipitation.rain.*
import pathfinder.weatherBot.weather.precipitation.snow.HeavySnow
import pathfinder.weatherBot.weather.precipitation.snow.LightSnow
import pathfinder.weatherBot.weather.precipitation.snow.MediumSnow
import pathfinder.weatherBot.weather.precipitation.snow.Sleet
import kotlin.math.max
import kotlin.math.min

enum class Intensity {
    LIGHT{
         override fun wet(hour: Hour) = when(1 d 100){
             in 1..20 -> LightFog(hour, 1 d 8)
             in 21..40 -> MediumFog(hour, 1 d 6)
             in 41..50 -> Drizzle(hour, 1 d 4)
             in 51..75 -> Drizzle(hour, 2 d 12)
             in 76..90 -> LightRain(hour, 1 d 4)
             in 91..100 -> if (hour.temp < 40) Sleet(hour, 1) else LightRain(hour, 1)
             else -> throw dHundredException
         }

        override fun frozen(hour: Hour) = when(1 d 100){
            in 1..20 -> LightFog(hour, 1 d 6)
            in 21..40 -> LightFog(hour, 1 d 8)
            in 41..50 -> MediumFog(hour, 1 d 4)
            in 51..60 -> LightSnow(hour, 1)
            in 61..75 -> LightSnow(hour, 1 d 4)
            in 76..100 -> LightSnow(hour, 2 d 12)
            else -> throw dHundredException
        }
    },
    MEDIUM {
        override fun wet(hour: Hour) = when(1 d 100){
            in 1..10 -> MediumFog(hour, 1 d 8)
            in 11..20 -> MediumFog(hour, 1 d 12)
            in 21..30 -> HeavyFog(hour, 1 d 4)
            in 31..35 -> MediumRain(hour, 1 d 4)
            in 36..70 -> MediumRain(hour, 1 d 8)
            in 71..90 -> MediumRain(hour, 2 d 12)
            in 91..100 -> if (hour.temp < 40) Sleet(hour, 1 d 4) else MediumRain(hour, 1 d 4)
            else -> throw dHundredException
        }

        override fun frozen(hour: Hour) = when(1 d 100){
            in 1..10 -> MediumFog(hour, 1 d 8)
            in 11..20 -> MediumFog(hour, 1 d 12)
            in 21..30 -> HeavyFog(hour, 1 d 4)
            in 31..50 -> MediumSnow(hour, 1 d 4)
            in 51..90 -> MediumSnow(hour, 1 d 8)
            in 91..100 -> LightSnow(hour, 2 d 12)
            else -> throw dHundredException
        }
    },
    HEAVY {
        override fun wet(hour: Hour) = when (1 d 100) {
            in 1..10 -> HeavyFog(hour, 1 d 8)
            in 11..20 -> HeavyFog(hour, 2 d 6)
            in 21..50 -> HeavyRain(hour, 1 d 12)
            in 51..70 -> HeavyRain(hour, 2 d 12)
            in 71..85 -> if (hour.temp < 40) Sleet(hour, 1 d 8) else HeavyRain(hour, 1 d 8)
            in 86..90 -> Thunderstorm(hour, 1)
            in 91..100 -> Thunderstorm(hour, 1 d 3)
            else -> throw dHundredException
        }

        override fun frozen(hour: Hour) = when (1 d 100) {
            in 1..10 -> MediumFog(hour, 1 d 8)
            in 11..20 -> HeavyFog(hour, 2 d 6)
            in 21..60 -> LightSnow(hour, 2 d 12)
            in 61..90 -> MediumSnow(hour, 1 d 8)
            in 91..100 -> HeavySnow(hour, 1 d 6)
            else -> throw dHundredException
        }
    };

    operator fun inc() = plus(1)
    operator fun dec() = minus(1)
    operator fun plus(i: Int) = values()[min(ordinal + i, values().lastIndex)]
    operator fun minus(i: Int) = values()[max(ordinal - i, 0)]
    abstract fun wet(hour: Hour): Precipitation
    abstract fun frozen(hour: Hour): Precipitation
}