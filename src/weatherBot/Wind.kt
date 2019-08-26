package weatherBot

enum class Wind(val description: String) {
    LIGHT(TODO()),
    MODERATE(TODO()),
    STRONG(TODO()),
    SEVERE(TODO()),
    WINDSTORM(TODO());
    companion object{
        operator fun invoke(): Wind = when(1 d 100){
            in 1..50 -> LIGHT
            in 51..80 -> MODERATE
            in 81..90 -> STRONG
            in 91..95 -> SEVERE
            in 96..100 -> WINDSTORM
            else -> throw dHundredException
        }
    }
}