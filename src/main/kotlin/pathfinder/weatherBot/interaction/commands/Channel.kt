package pathfinder.weatherBot.interaction.commands

import com.jagrosh.jdautilities.command.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData
import org.springframework.stereotype.Service
import pathfinder.weatherBot.service.ClientService

@Service
class Channel(
    private val clientService: ClientService
) : SlashCommandInterface("channel", "Sets the output channel.") {

    init {
        options.add(OptionData(OptionType.CHANNEL, "channel", "output channel"))
    }

    override fun execute(event: SlashCommandEvent) {
        event.deferReply(true).queue { hook ->
            val channel = event.getOption("channel")?.asChannel ?: event.messageChannel
            clientService.perform(event.guild!!) { client ->
                client.config.outputChannel = channel.idLong
            }
            hook.editOriginal("Output channel set to ${channel.asMention}.").queue()
        }
    }
}
