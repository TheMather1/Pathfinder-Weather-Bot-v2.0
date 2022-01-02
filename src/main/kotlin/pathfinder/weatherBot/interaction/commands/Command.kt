package pathfinder.weatherBot.interaction.commands

import net.dv8tion.jda.api.Permission.MANAGE_SERVER
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyAction
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import pathfinder.weatherBot.interaction.Client

interface Command {
    val commandData: CommandData
    val sudo: Boolean

    fun execute(event: SlashCommandEvent, client: Client): ReplyAction

    fun evaluate(event: SlashCommandEvent, client: Client) = if (sudo && !event.sudo)
        event.reply("Only moderators may use this command.")
    else execute(event, client).also {
        logger.info("Attempted command ${commandData.name} with options: ${event.options}")
    }

    private val SlashCommandEvent.sudo: Boolean
        get() = member?.hasPermission(MANAGE_SERVER) ?: false

    companion object {
        val logger: Logger = LoggerFactory.getLogger(Command::class.java)
    }
}