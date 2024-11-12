package pathfinder.weatherBot.weather.precipitation

import pathfinder.weatherBot.weather.precipitation.Rain.THUNDERSTORM
import pathfinder.weatherBot.weather.precipitation.Snow.BLIZZARD
import pathfinder.weatherBot.weather.precipitation.Snow.HEAVY_SNOW
import pathfinder.weatherBot.weather.precipitation.Snow.MEDIUM_SNOW

enum class Snow : Precipitation {
    SLEET {
        override val fireRetardance = 25
        override fun describeChange(prev: Precipitation?) = when(prev) {
            SLEET -> null
            BLIZZARD -> "The blizzard yields as temperatures pick up, the fierce snowstorm giving way to frosty sleet."
            is Snow -> "The snowfall begins to melt into a wet sleet."
            is Rain -> "The rain partially freezes into a wet sleet."
            is Fog -> "The fog is washed away as wet sleet begins to fall."
            is None -> "Wet sleet begins dusting the area."
            else -> "Wet sleet is falling through the air."
        }

        override val finished: String
            get() = "The sleet came and went, leaving very few traces of its occurrence behind."
    },
    LIGHT_SNOW {
        override val fireRetardance = 5
        override fun describeChange(prev: Precipitation?) = when(prev) {
            LIGHT_SNOW -> null
            BLIZZARD -> "The blizzard yields, and only a light snowfall remains."
            SLEET -> "The sleet freezes into a light dusting of snow."
            is Snow -> "The snow slows to a gentle dusting."
            is Rain -> "The rain freezes into a light snow."
            is Fog -> "The fog dissipates as a light dusting of snow begins to fall."
            is None -> "Snowflakes flutter down from the sky, gently dusting the ground."
            else -> "Fine snow dances in the air."
        }


        override val finished: String
            get() = "The gentle snowing has ended."
    },
    MEDIUM_SNOW {
        override val fireRetardance = 25

        override fun fall() {
//        hour.day.forecast.biome.snowLevel += 1
        }

        override fun describeChange(prev: Precipitation?) = when(prev) {
            MEDIUM_SNOW -> null
            BLIZZARD -> "The blizzard yields, and only a moderate snowfall remains."
            HEAVY_SNOW -> "The snowfall slows slightly, but is still rapidly blanketing the ground."
            is Snow -> "The snowfall picks up, and is now rapidly blanketing the ground."
            is Rain -> "The rain freezes into a considerable snowfall, rapidly blanketing the ground."
            is Fog -> "The fog dissipates as a considerable snowfall begins rapidly blanketing the ground."
            is None -> "Snow begins falling from the sky and begins rapidly blanketing the ground."
            else -> "Snow is falling upon the area."
        }


        override val finished: String
        get() = "The snowing gradually ends."
    },
    HEAVY_SNOW {
        override val fireRetardance = 75

        override fun describeChange(prev: Precipitation?) = when (prev) {
            THUNDER_BLIZZARD, BLIZZARD -> "The blizzard yields, but the air is still heavy with snow."
            THUNDERSNOW -> "The sound of thunder yields, but the air is still heavy with snow."
            HEAVY_SNOW -> null
            is Snow -> "The snow intensifies into a thick blanket in the air, piling onto the ground."
            is Rain -> "The rain freezes into a heavy blanket of snow, which starts piling up on the ground."
            is Fog -> "The fog gives way to a heavy blanket of snow, which starts piling up on the ground."
            is None -> "The air is heavy with snow, which rapidly piles up on the ground."
            else -> "An intense snowstorm blankets the area."
        }

        override val finished: String
        get() = "The heavy pillowing of snow stops."
    },
    BLIZZARD {
        override val fireRetardance = HEAVY_SNOW.fireRetardance
        override fun describeChange(prev: Precipitation?) = when(prev) {
            BLIZZARD -> null
            is Snow -> "Intense winds turns the snow into a blizzard."
            is Rain -> "The rain freezes as a blizzard picks up."
            is Fog -> "The fog is blown away as a blizzard picks up."
            is None -> "Heavy snow and high winds combine into a blizzard."
            else -> "There is a blizzard buffeting the area."
        }

        override val finished: String
            get() = "Leaving behind a heaping helping of snow, the blizzard has passed us!"
    },
    THUNDERSNOW {
        override val fireRetardance = HEAVY_SNOW.fireRetardance
        override fun describeChange(prev: Precipitation?) = when (prev) {
            THUNDERSNOW -> null
            THUNDER_BLIZZARD -> "The blizzard ceases as the winds calm, but the snowfall and thunder still linger."
            THUNDERSTORM -> "The rain freezes into a heavy snowfall, as thunder keeps roaring out in the distance."
            HEAVY_SNOW -> "Muffled slightly by the heavy blanket of snow, thunder roars out in the distance."
            is Snow -> "The snow picks up into a heavy snowfall, as thunder roars out in the distance."
            is None -> "A heavy blanketing of snow starts to fall, accompanied by thunder and lightning."
            else -> "A heavy snowstorm is accompanied by roaring thunder."
        }?.plus(" Be careful about walking outside in metal armor.")

        override val finished: String
            get() = "The thunder rolls away, as does the snow."
    },
    THUNDER_BLIZZARD {
        override val fireRetardance = BLIZZARD.fireRetardance
        override fun describeChange(prev: Precipitation?) = when (prev) {
            THUNDER_BLIZZARD -> null
            BLIZZARD -> "The blizzard holds strong as thunder starts to roar out in the distance."
            THUNDERSNOW -> "Intense winds turn the thundersnow into a thunder blizzard."
            is Snow -> "Intense winds turns the snow into a blizzard as thunder roars out in the distance."
            is None -> "An intense blizzard rolls in as thunder roars out in the distance"
            else -> "An intense blizzard is accompanied by roaring thunder."
        }?.plus(" Be careful about walking outside in metal armor.")

        override val finished: String
            get() = "The thunder blizzard clears out."
    };

    override fun fall() { }
}