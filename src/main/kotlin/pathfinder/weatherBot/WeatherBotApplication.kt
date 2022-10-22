package pathfinder.weatherBot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class WeatherBotApplication: SpringBootServletInitializer(){

    override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder = builder.sources(javaClass)
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<WeatherBotApplication>(*args)
        }
    }
}
