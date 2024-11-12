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
            }?.plus(" (All vision beyond 5 ft. is obscured. Creatures more than 5 ft. away have concealment.)")

            override val finished: String
            get() = "The obscuring fog dissipates."
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
        }?.plus(" (Sight is reduced 1/2 range. Take a -4 to perception and ranged attack rolls.)")

        override val finished: String
        get() = "The fog fades away, sight is no longer obscured."
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
        }?.plus(" (Sight is reduced to 3/4 range, and you suffer a -2 to perception and ranged attacks.)")

        override val finished: String
            get() = "The mist dissipates, we can see clearly now!"
    };

    override fun fall() { }
}