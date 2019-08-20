package weatherBot.precipitation.snow

import weatherBot.precipitation.HeavySnow
import weatherBot.precipitation.Thunder

class Thundersnow(override val duration: Int) : HeavySnow, Thunder {
}