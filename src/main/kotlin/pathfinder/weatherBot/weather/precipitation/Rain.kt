package pathfinder.weatherBot.weather.precipitation

enum class Rain : Precipitation {
    DRIZZLE {
        override val fireRetardance = 5
        override fun describeChange(prev: Precipitation?) = when (prev) {
            DRIZZLE -> null
            is Fog -> "The fog is washed away by a drizzle overhead."
            is Rain -> "The rain slows to a light drizzle."
            is Snow -> "As temperatures rise, the falling snowflakes turn into water droplets."
            is None -> "A refreshing drizzle covers the area with a fine coat of droplets."
            else -> "There is a light drizzle."
        }

        override val finished = "The drizzle ends."
        override val warn = """Drizzle:
    Visibility range is reduced to three-quarters. Perception checks suffer a –2 penalty. Tiny unprotected flames (candles and the like, but not torches) are automatically extinguished."""
        override val iconUrl = "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/overcast-drizzle.png?raw=true"
    },
    LIGHT_RAIN {
        override val fireRetardance = 10
        override fun describeChange(prev: Precipitation?) = when (prev) {
            LIGHT_RAIN -> null
            DRIZZLE -> "The rain picks up into a light shower."
            is Fog -> "Light rain showers down, washing away the fog."
            THUNDERSTORM -> "The sound of thunder yields, and the rain fades to a light sprinkling."
            is Rain -> "The rain lightens, but doesn't stop."
            is Snow -> "As temperatures rise, the snowfall turns into a light shower."
            is None -> "Rain lightly pours from the gray clouds lining the sky."
            else -> "There is a light rain."
        }

        override val finished = "The sprinkling lets up."
        override val iconUrl = "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/overcast-rain.png?raw=true"
    },
    MEDIUM_RAIN {
        override val fireRetardance = 15
        override fun describeChange(prev: Precipitation?) = when (prev) {
            MEDIUM_RAIN -> null
            is Fog -> "Rain pours down, washing away the fog."
            THUNDERSTORM -> "The sound of thunder yields, and the rain calms somewhat."
            DRIZZLE, LIGHT_RAIN -> "The begins falling somewhat harder."
            HEAVY_RAIN -> "The rain begins to calm down a bit."
            is Snow -> "As temperatures rise, the snow gives way to rain."
            is None -> "A healthy rainfall pours overhead."
            else -> "It is raining."
        }

        override val finished = "The rainfall ceases."
        override val iconUrl = "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/overcast-rain.png?raw=true"
    },
    HEAVY_RAIN  {
        override val fireRetardance = 25
        override fun describeChange(prev: Precipitation?) = when (prev) {
            THUNDERSTORM -> "The sound of thunder yields, but the rain remains."
            HEAVY_RAIN -> null
            is Fog -> "A heavy rain shower starts, washing away the fog."
            is Rain -> "The rain begins beating down heavier and heavier.."
            is Snow -> "As temperatures rise, the snow gives way to a lashing rain."
            is None -> "Dark, heavy clouds begin pouring rain across the area."
            else -> "It is raining heavily."
        }

        override val finished: String
            get() = "The clouds have been drained, and the downpour ends."
        override val warn = """Heavy rain:
    Visibility range is quartered. Ranged attacks and perception checks suffer a -6 penalty. Unprotected flames are automatically extinguished."""
        override val iconUrl = "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/extreme-rain.png?raw=true"
    },
    THUNDERSTORM {
        override val fireRetardance = HEAVY_RAIN.fireRetardance
        override val isThunder = true
        override fun describeChange(prev: Precipitation?) = when (prev) {
            THUNDERSTORM -> null
            is Fog -> "With a earth-shaking boom, a thunderstorm scares off the fog."
            HEAVY_RAIN -> "The downpour grows into a thunderstorm!"
            is Rain -> "The rain suddenly begins pouring harder, and white streaks of lightning generate from the clouds."
            is Snow -> "The rising temperatures cause the snowfall to escalate into a fully-fledged thunderstorm!"
            is None -> "With blackened clouds pouring to set the mood, a white flash and loud cracks from the sky, it's apparent that a thunderstorm is happening!"
            else -> "There is a roaring thunderstorm."
        }?.plus(" Be careful about walking outside in metal armor.")

        override val finished: String
            get() = "The sounds of thunder cease, and soon after, the shower as well."
        override val warn = HEAVY_RAIN.warn
        override val iconUrl = "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/thunderstorms-extreme-rain.png?raw=true"
    };

    override fun fall() { }
    override val warn = """Rain:
    Visibility range is halved. Ranged attacks and Perception checks suffer a –4 penalty. Unprotected flames (candles, torches, and the like) are automatically extinguished."""
}