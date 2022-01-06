package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import org.springframework.stereotype.Service
import pathfinder.weatherBot.interaction.Client

@Service
class Channel: WeatherCommand {
    override val commandData = CommandData("channel", "Sets the output channel.")
        .addOption(OptionType.CHANNEL, "channel", "output channel")
    override val sudo = true

    override fun execute(event: SlashCommandEvent, client: Client): String {
        val channel = event.getOption("channel")?.asGuildChannel ?: event.textChannel
        client.config.outputChannel = channel.idLong
        return "Output channel set to ${channel.asMention}."
    }
}