package pathfinder.weatherBot.config

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent.GUILD_MEMBERS
import org.mapdb.DBMaker
import org.mapdb.HTreeMap
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.interaction.commands.WeatherCommand

@Configuration
@ConfigurationProperties("pathfinder.weather.bot")
class BotConfig {
    private val db = DBMaker.fileDB("session_data.db").closeOnJvmShutdown().make()
    lateinit var token: String

    @Bean
    fun registrations(weatherCommands: List<WeatherCommand>): HTreeMap<Long, Client> {
        return db.hashMap("client_registrations").createOrOpen() as HTreeMap<Long, Client>
    }

    @Bean
    fun bot() =
        JDABuilder.createDefault(token, GUILD_MEMBERS).setActivity(Activity.watching("the skies"))
            .setRelativeRateLimit(false).setRequestTimeoutRetry(false).setMaxReconnectDelay(32)
            .build()
}