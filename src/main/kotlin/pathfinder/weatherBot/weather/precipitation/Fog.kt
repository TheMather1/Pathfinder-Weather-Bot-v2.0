package pathfinder.weatherBot.weather.precipitation

enum class Fog : Precipitation {
    HEAVY_FOG {
        override val fireRetardance = 10
        override fun describeChange(prev: Precipitation?) = when (prev) {
            HEAVY_FOG -> null
            is Fog -> "The fog thickens into a smothering brume."
            is Rain -> "The rain stops, allowing a heavy fog to form."
            is Snow -> "The snowfall ceases, letting a soupy-thick fog obscure our vision."
            is None -> "A thick fog rolls in."
            else -> "There is a heavy blanket of fog."
        }

        override val finished = "The obscuring fog dissipates."
        override val warn = """Heavy fog:
    All vision beyond 5 ft. is obscured. Creatures beyond 5 ft. have concealment."""
        override val iconUrl = "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/extreme-fog.png?raw=true"
    },
    MEDIUM_FOG {
        override val fireRetardance = 5
        override fun describeChange(prev: Precipitation?) = when (prev) {
            MEDIUM_FOG -> null
            LIGHT_FOG -> "The mist thickens into a fully-fledged fog."
            HEAVY_FOG -> "The thick haze fades slightly into a moderate fog."
            is Rain -> "The rain passes as a fog falls upon the area."
            is Snow -> "As the snow stops, it gives way to a fog."
            is None -> "An obscuring fog rolls in."
            else -> "The area is covered in fog."
        }

        override val finished = "The fog fades away, sight is no longer obscured."
        override val warn = """Fog:
    Visibility range is halved. Ranged attacks and Perception checks suffer a –4 penalty."""
        override val iconUrl = "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/overcast-fog.png?raw=true"
    },
    LIGHT_FOG {
        override val fireRetardance = 0
        override fun describeChange(prev: Precipitation?) = when (prev) {
            LIGHT_FOG -> null
            is Fog -> "The fog fades to a thin veil."
            is Rain -> "As the clouds empty, a light mist hangs in the air..."
            is Snow -> "The snowfall relents and a fine mist remains in its place."
            is None -> "A fine mist falls upon the area."
            else -> "A fine mist covers the area."
        }

        override val finished: String
            get() = "The mist dissipates, we can see clearly now!"
        override val warn = """Light fog:
    Visibility range is reduced to three-quarters. Perception checks suffer a –2 penalty."""
        override val iconUrl = "https://github.com/basmilius/weather-icons/blob/dev/production/fill/png/1024/overcast-haze.png?raw=true"
    };

    override fun fall() { }
}