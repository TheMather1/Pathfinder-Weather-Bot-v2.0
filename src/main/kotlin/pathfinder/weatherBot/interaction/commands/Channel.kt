package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.moderatorPermission
import javax.annotation.PostConstruct

@Service
class Channel : WeatherCommand("channel", "Sets the output channel.") {


    @PostConstruct
    fun configureOptions() {
        addOption(OptionType.CHANNEL, "channel", "output channel")
        defaultPermissions = moderatorPermission
    }

    override fun execute(event: SlashCommandInteractionEvent, client: Client): String {
        val channel = event.getOption("channel")?.asChannel ?: event.messageChannel
        client.config.outputChannel = channel.idLong
        return "Output channel set to ${channel.asMention}."
    }
}
