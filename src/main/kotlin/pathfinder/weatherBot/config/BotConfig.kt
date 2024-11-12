package pathfinder.weatherBot.config

import com.jagrosh.jdautilities.command.CommandClient
import com.jagrosh.jdautilities.command.CommandClientBuilder
import com.jagrosh.jdautilities.command.SlashCommand
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent.GUILD_MEMBERS
import net.dv8tion.jda.api.utils.cache.CacheFlag.EMOJI
import net.dv8tion.jda.api.utils.cache.CacheFlag.SCHEDULED_EVENTS
import net.dv8tion.jda.api.utils.cache.CacheFlag.STICKER
import net.dv8tion.jda.api.utils.cache.CacheFlag.VOICE_STATE
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pathfinder.weatherBot.interaction.GuildMembershipEventListener

@Configuration
@ConfigurationProperties("pathfinder.weather.bot")
class BotConfig {
    private val logger = LoggerFactory.getLogger(javaClass)

    lateinit var token: String

    @Bean
    fun commandClient(slashCommands: Array<SlashCommand>): CommandClient =
        CommandClientBuilder().setOwnerId(108971970074279936L).addSlashCommands(*slashCommands).build()

    @Bean
    fun bot(commandClient: CommandClient, guildMembershipEventListener: GuildMembershipEventListener) =
        JDABuilder.createDefault(token, GUILD_MEMBERS)
            .disableCache(VOICE_STATE, EMOJI, STICKER, SCHEDULED_EVENTS)
            .setRequestTimeoutRetry(false).setMaxReconnectDelay(32)
            .build().apply {
                addEventListener(commandClient, guildMembershipEventListener)
                logger.info("Adding commands: ${commandClient.slashCommands.map { it.name }}")
                awaitReady()
                unavailableGuilds.forEach {
                    logger.info("Unavailable guild: $it")
                }
                presence.activity = Activity.watching("the skies")
            }
}
