package pathfinder.weatherBot.time

import pathfinder.weatherBot.weather.Weather
import java.io.Serializable
import java.time.LocalDateTime

data class Hour(val weather : Weather, val time: LocalDateTime): Serializable