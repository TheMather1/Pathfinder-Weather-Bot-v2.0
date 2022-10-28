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
import kotlin.properties.Delegates

@Configuration
@ConfigurationProperties("pathfinder.weather.bot")
class BotConfig {
    @get:Bean
    val db = DBMaker.fileDB("/efs/session_data.db")
        .closeOnJvmShutdown()
        .checksumHeaderBypass()
        .fileMmapEnableIfSupported()
        .fileChannelEnable()
        .make()
    lateinit var token: String
    var shardId by Delegates.notNull<Int>()
    var shardTotal by Delegates.notNull<Int>()

    @Bean
    fun registrations(): HTreeMap<Long, Client> {
        return db.hashMap("client_registrations").createOrOpen() as HTreeMap<Long, Client>
    }

    @Bean
    fun bot() =
        JDABuilder.createDefault(token, GUILD_MEMBERS).useSharding(shardId, shardTotal).setActivity(Activity.watching("the skies"))
            .setRelativeRateLimit(false).setRequestTimeoutRetry(false).setMaxReconnectDelay(32)
            .build()
}
