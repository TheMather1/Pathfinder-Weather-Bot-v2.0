package weatherBot.precipitation.controller

import weatherBot.precipitation.Intensity

interface Controller {
    operator fun invoke(intensity: Intensity)
}