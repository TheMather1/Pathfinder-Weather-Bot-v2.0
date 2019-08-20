package weatherBot.precipitation.snow

import weatherBot.precipitation.Blizzard
import weatherBot.precipitation.Thunder

class ThunderBlizzard(override val duration: Int) : Blizzard, Thunder {

}