package pathfinder.weatherBot.config

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent.GUILD_MEMBERS
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("pathfinder.weather.bot")
class BotConfig {

    lateinit var token: String

    @Bean
    fun bot() =
        JDABuilder.createDefault(token, GUILD_MEMBERS).setActivity(Activity.watching("the skies"))
            .setRequestTimeoutRetry(false).setMaxReconnectDelay(32)
            .build()
}
