package pathfinder.weatherBot.interaction.commands

import com.jagrosh.jdautilities.command.SlashCommandEvent
import net.dv8tion.jda.api.Permission
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SettingsCommand(
    @Value("\${baseUrl}")
    private val baseUrl: String
) : SlashCommandInterface("settings", "Change the bot settings.") {

    init {
        userPermissions = arrayOf(Permission.MANAGE_SERVER)
    }

    override fun execute(event: SlashCommandEvent) {
        event.deferReply(true).queue {
            it.editOriginal("Settings can be found [here](${baseUrl}/portal/${event.guild!!.idLong}/settings)").queue()
        }
    }
}
