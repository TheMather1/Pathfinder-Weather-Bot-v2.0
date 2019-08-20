package weatherBot.precipitation.snow

interface Blizzard: HeavySnow {
    override fun invoke(duration: Int) {
        if (thunder()) ThunderBlizzard(duration)
        else BlizzardImpl(duration)
    }
}