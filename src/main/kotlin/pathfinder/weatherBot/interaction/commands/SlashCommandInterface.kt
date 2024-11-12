package pathfinder.weatherBot.interaction.commands

import com.jagrosh.jdautilities.command.SlashCommand

abstract class SlashCommandInterface(name: String, help: String) : SlashCommand() {
    init {
        super.name = name
        super.help = help
    }
}
