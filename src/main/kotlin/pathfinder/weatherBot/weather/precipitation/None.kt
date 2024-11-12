package pathfinder.weatherBot.weather.precipitation

enum class None : Precipitation {
    NONE {
        override fun fall() { /*Do nothing*/ }

        override val fireRetardance: Int = 0

        override fun describeChange(prev: Precipitation?): String?  = prev?.takeUnless { it is None }?.finished

        override val finished: String
            get() = throw IllegalStateException("Precipitation type None has no end description.")
    }
}
