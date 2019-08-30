package pathfinder.weatherBot

import java.net.URL

object Api {
    val webhook_url = URL(config.getProperty("WEBHOOK_URL"))
    val forecast_url = URL(config.getProperty("FORECAST_URL"))

    fun buildMessage(content: String): String = TODO()
}