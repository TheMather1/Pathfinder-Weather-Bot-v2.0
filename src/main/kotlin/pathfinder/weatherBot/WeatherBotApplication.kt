package pathfinder.weatherBot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class WeatherBotApplication{
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<WeatherBotApplication>(*args)
        }
    }
}
