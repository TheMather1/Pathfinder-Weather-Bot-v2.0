package weatherBot.precipitation.controller

import weatherBot.precipitation.Precipitation
import java.time.LocalDate

interface Controller {
    operator fun invoke(temp: Long, date: LocalDate): Precipitation
}