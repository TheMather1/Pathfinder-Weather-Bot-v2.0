package pathfinder.weatherBot.config

import club.minnced.jda.reactor.ReactiveEventManager
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.requests.GatewayIntent.GUILD_MEMBERS
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pathfinder.weatherBot.interaction.Client

@Configuration
@ConfigurationProperties("pathfinder.weather.bot")
class BotConfig {
    lateinit var token: String
//    lateinit var registrations: MutableMap<Long, Client>

    @Bean
    fun bot(eventManager: ReactiveEventManager) = JDABuilder.createDefault(token)
        .setActivity(Activity.watching("the skies"))
        .setEventManager(eventManager)
        .enableIntents(GUILD_MEMBERS)
        .build()

    @Bean
    fun registrations(): MutableMap<Long, Client> {
        return HashMap()
    }
}