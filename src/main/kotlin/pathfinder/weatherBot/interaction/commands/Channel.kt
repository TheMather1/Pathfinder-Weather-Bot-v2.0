package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client
import javax.annotation.PostConstruct

@Service
class Channel : WeatherCommand("channel", "Sets the output channel.") {


    @PostConstruct
    fun configureOptions() {
        addOption(OptionType.CHANNEL, "channel", "output channel")
    }

    override val sudo = true

    override fun execute(event: SlashCommandEvent, client: Client): String {
        val channel = event.getOption("channel")?.asGuildChannel ?: event.textChannel
        client.config.outputChannel = channel.idLong
        return "Output channel set to ${channel.asMention}."
    }
}
