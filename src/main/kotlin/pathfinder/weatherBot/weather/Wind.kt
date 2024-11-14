package pathfinder.weatherBot.weather

import pathfinder.diceSyntax.d
import pathfinder.weatherBot.dHundredException

enum class Wind {
    LIGHT {
        override fun describeChange(prevWind: Wind?) = when {
            prevWind == null -> "The air is perfectly still save the occasional breeze."
            prevWind == HURRICANE -> "The hurricane passes, leaving a barely perceptible breeze in its wake."
            prevWind == TORNADO -> "The tornado peters out into a barely perceptible breeze."
            prevWind > this -> "The wind slows to a near standstill."
            prevWind < this -> "Either you survived total vacuum, or the bot bugged out."
            else -> null
        }
    },
    MODERATE {
        override fun describeChange(prevWind: Wind?) = when {
            prevWind == null -> "There is a calm wind."
            prevWind == HURRICANE -> "The hurricane passes, yielding way to calm winds."
            prevWind == TORNADO -> "The tornado peters out into calm winds."
            prevWind > this -> "The forceful gusts have calmed to a stable wind."
            prevWind < this -> "A calm wind picks up."
            else -> null
        }

        override val warn = """Wind:
    Tiny unprotected flames (candles and the like, but not torches) have a 50% chance of being extinguished."""
    },
    STRONG {
        override fun describeChange(prevWind: Wind?) = when {
            prevWind == null -> "Gusts of strong wind buffet the area."
            prevWind == HURRICANE -> "The hurricane passes, yielding way to more manageable winds."
            prevWind == TORNADO -> "The tornado peters out into more manageable wind."
            prevWind > this -> "The wind slows to more bearable gusts."
            prevWind < this -> "Strong gusts of wind begin to buffet across the area, kicking up dust."
            else -> null
        }
        override val warn = """Strong wind:
    Ranged attacks, Fly checks, and Perception checks that rely on sound suffer a -2 penalty.
    Tiny or smaller creatures moving against the wind must succeed at a DC 10 Strength check to move against the wind, or DC 20 Fly check if flying.
    Unprotected flames are automatically extinguished."""
    },
    SEVERE {
        override fun describeChange(prevWind: Wind?) = when {
            prevWind == null -> "An intense gale buffets the area."
            prevWind == WINDSTORM -> "The windstorm gradually calms to a gale."
            prevWind == HURRICANE -> "The hurricane passes, leaving behind gale winds."
            prevWind == TORNADO -> "The tornado peters out into howling winds."
            prevWind < this -> "An intense gale gale flies across the area, kicking up debris."
            else -> null
        }

        override val warn = """Severe wind:
    Ranged attacks, Fly checks, and Perception checks that rely on sound suffer a -4 penalty.
    Small or smaller creatures moving against the wind must succeed at a DC 10 Strength check to move against the wind, or DC 20 Fly check if flying.
    Tiny or smaller creatures must succeed at a DC 15 Strength check to avoid being knocked prone and rolling 1d4*10 ft. taking 1d4 nonlethal damage per 10 ft., or a DC 25 Fly check to avoid being flung back 2d6*10 ft. and taking 2d6 nonlethal damage if flying.
    Unprotected flames are automatically extinguished. Protected flames (like lanterns) have a 50% chance of being extinguished."""
    },
    WINDSTORM {
        override fun describeChange(prevWind: Wind?) = when {
            prevWind == null -> "A dangerous windstorm rages across the area."
            prevWind == HURRICANE -> "The hurricane passes, but the wind remains intense."
            prevWind == TORNADO -> "The tornado peters out, but the wind remains intense."
            prevWind < this -> "A windstorm kicks up, threatening to blow away anyone wandering outside!"
            else -> null
        }

        override val warn = """Severe wind:
    Ranged attacks are impossible. Ranged siege attacks suffer a -4 penalty. Fly checks and Perception checks that rely on sound suffer a -8 penalty.
    Medium or smaller creatures moving against the wind must succeed at a DC 10 Strength check to move against the wind, or DC 20 Fly check if flying.
    Small or smaller creatures must succeed at a DC 15 Strength check to avoid being knocked prone and rolling 1d4*10 ft. taking 1d4 nonlethal damage per 10 ft., or a DC 25 Fly check to avoid being flung back 2d6*10 ft. and taking 2d6 nonlethal damage if flying.
    Unprotected flames are automatically extinguished. Protected flames (like lanterns) have a 75% chance of being extinguished."""
    },
    HURRICANE {
        override fun describeChange(prevWind: Wind?) = when {
            prevWind == null -> "A massive hurricane is wreaking havoc the area."
            prevWind == TORNADO -> "The tornado dies out, but the area is still under the blanket of a hurricane."
            prevWind < this -> "Warm, humid winds carry with them a tropical hurricane, which rolls over the area."
            else -> null
        }

        override val warn = """Hurricane:
    Ranged attacks and Perception checks that rely on hearing are impossible. Ranged siege attacks suffer a -8 penalty. Fly checks suffer a -12 penalty.
    Large or smaller creatures moving against the wind must succeed at a DC 15 Strength check to move against the wind.
    Medium or smaller creatures must succeed at a DC 15 Strength check to avoid being knocked prone and rolling 1d6*10 ft. taking 1d6 nonlethal damage per 10 ft.
    Flying creatures must succeed at a DC 25 Fly check to avoid being flung back 2d8*10 ft. and taking 4d6 nonlethal damage.
    All flames are automatically extinguished."""

    },
    TORNADO {
        override fun describeChange(prevWind: Wind?) = when {
            prevWind == null -> "A tornado rampages across the area."
            prevWind == HURRICANE -> "The hurricane winds form into a tornado."
            prevWind > this ->  "If you get this message, either we survived apocalyptic-levels of wind, or the bot bugged out."
            prevWind < this -> "The wind picks up and forms into a rampaging tornado."
            else -> null
        }

        override val warn = """Tornado:
    Ranged attacks, including ranged siege attacks and evocation spells, and Perception checks that rely on hearing are impossible. Fly checks suffer a -16 penalty.
    Huge or smaller creatures must succeed at a DC 20 Strength check to avoid being knocked prone sucked 1d4*10 ft. towards the tornado taking 1d4 nonlethal damage per 10 ft., or a DC 25 Fly check to avoid being sucked in 2d6*10 ft. and taking 2d6 nonlethal damage if flying.
    All flames are automatically extinguished."""

    };

    companion object {
        operator fun invoke() = when ((1 d 100).toLong()) {
            in 1..50 -> LIGHT
            in 51..80 -> MODERATE
            in 81..90 -> STRONG
            in 91..95 -> SEVERE
            in 96..100 -> WINDSTORM
            else -> throw dHundredException
        }
    }

    abstract fun describeChange(prevWind: Wind?): String?
    operator fun plus(i: Int) = entries[(ordinal + i).coerceAtMost(entries.size - 1)]
    operator fun minus(i: Int) = entries[(ordinal - i).coerceAtLeast(0)]
    override fun toString() = this.name.lowercase().replaceFirstChar { it.uppercaseChar() }
    open val warn: String? = null
}
