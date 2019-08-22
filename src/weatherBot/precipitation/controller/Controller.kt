package weatherBot.precipitation.controller

import weatherBot.precipitation.Intensity
import weatherBot.precipitation.Precipitation

interface Controller {
    operator fun invoke(intensity: Intensity): Precipitation
}