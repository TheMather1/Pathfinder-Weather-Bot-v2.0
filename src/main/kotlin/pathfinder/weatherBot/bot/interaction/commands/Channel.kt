package pathfinder.weatherBot.bot.interaction.commands

import net.dv8tion.jda.api.entities.TextChannel
import pathfinder.weatherBot.bot.interaction.CommandHandler

class Channel(handler: CommandHandler) : Command(handler) {
    override val command = "channel"
    override val description = "Sets the output channel."
    override val supportedParameterCounts = arrayOf(0, 1)
    override val sudo = true
    var newChannel: TextChannel? = null

    override fun execute() {
        handler.bot?.setChannel(channel)
    }

    override fun execute(params: List<String>) {
        if (newChannel != null) handler.bot?.setChannel(newChannel!!)
        else TODO("Channel not recognized.")
    }

    override fun help() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}