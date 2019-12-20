package pathfinder.weatherBot.bot.interaction

import net.dv8tion.jda.api.entities.TextChannel
import pathfinder.weatherBot.bot.interaction.commands.Channel
import pathfinder.weatherBot.bot.interaction.commands.Command

class UserInput(input: String, private val sudo: Boolean, handler: CommandHandler) {
    private var command: Command?
    private lateinit var parameters: List<String>

    init {
        val inputArray = input.split(' ')
        command = handler.commands[inputArray.first()]
        parameters = inputArray.drop(1)
    }

    internal fun execute(channel: TextChannel, newChannel: TextChannel?) {
        try {
            command!!.channel = channel
            if (command is Channel) (command as Channel).newChannel = newChannel
            command!!.evaluate(parameters, sudo)
        } catch (_: NullPointerException) {
            TODO("Command does not exist.")
        } catch (_: UnsupportedOperationException) {
            TODO("Unsupported parameter count.")
        } catch (_: IllegalAccessError) {
            TODO("User not authorized to use command.")
        }
    }
}