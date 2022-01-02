package pathfinder.weatherBot.config

import club.minnced.jda.reactor.ReactiveEventManager
import club.minnced.jda.reactor.on
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.requests.GatewayIntent.GUILD_MEMBERS
import org.mapdb.DBMaker
import org.mapdb.HTreeMap
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.interaction.commands.Command
import reactor.util.Loggers
import java.util.concurrent.ConcurrentMap

@Configuration
@ConfigurationProperties("pathfinder.weather.bot")
class BotConfig {
    lateinit var token: String
    val db = DBMaker.fileDB("session_data.db").closeOnJvmShutdown().make()
    private val logger = Loggers.getLogger(javaClass)

    @Bean
    fun registrations(commands: List<Command>): ConcurrentMap<Long, Client> {
        return db.hashMap("client_registrations").createOrOpen() as HTreeMap<Long, Client>
    }

    @Bean
    fun eventManager(registrations: Map<Long, Client>, commands: List<Command>) = ReactiveEventManager().apply {
        on<ReadyEvent>().log(logger).subscribe()
        on<SlashCommandEvent>().log(logger).mapNotNull { event ->
            val command = commands.firstOrNull { event.name == it.commandData.name }
            command?.evaluate(event, registrations[event.guild!!.idLong]!!)
        }.subscribe { it?.queue() }
    }

    @Bean
    fun bot(eventManager: ReactiveEventManager, commands: List<Command>) =
        JDABuilder.createDefault(token)
            .setActivity(Activity.watching("the skies"))
            .setEventManager(eventManager)
            .enableIntents(GUILD_MEMBERS)
            .build().configure(commands)

    fun JDA.configure(commands: List<Command>): JDA {
        Client.jda = this
        commands.forEach { upsertCommand(it.commandData).queue() }
        return this
    }
}