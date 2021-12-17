package pathfinder.weatherBot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WeatherBotApplication{
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<WeatherBotApplication>(*args)
        }
    }
}
