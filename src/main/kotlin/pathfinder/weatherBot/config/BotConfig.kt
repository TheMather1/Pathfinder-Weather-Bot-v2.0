package pathfinder.weatherBot.config

import club.minnced.jda.reactor.ReactiveEventManager
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent.GUILD_MEMBERS
import org.mapdb.DBMaker
import org.mapdb.HTreeMap
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pathfinder.weatherBot.interaction.Client
import java.util.concurrent.ConcurrentMap

@Configuration
@ConfigurationProperties("pathfinder.weather.bot")
class BotConfig {
    lateinit var token: String
    val db = DBMaker.fileDB("session_data.db").closeOnJvmShutdown().make()
//    lateinit var registrations: MutableMap<Long, Client>

    @Bean
    fun bot(eventManager: ReactiveEventManager) = JDABuilder.createDefault(token)
        .setActivity(Activity.watching("the skies"))
        .setEventManager(eventManager)
        .enableIntents(GUILD_MEMBERS)
        .build()

    @Bean
    fun registrations(): ConcurrentMap<Long, Client> {
        return db.hashMap("client_registrations").createOrOpen() as HTreeMap<Long, Client>
    }
}