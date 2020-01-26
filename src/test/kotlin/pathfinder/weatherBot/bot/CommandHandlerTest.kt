package pathfinder.weatherBot.bot

import org.junit.jupiter.api.Test
import pathfinder.weatherBot.bot.interaction.Client
import pathfinder.weatherBot.bot.interaction.CommandHandler

internal class CommandHandlerTest{
    val commandHandler = CommandHandler(Client("bogus", "bogus"))

    @Test
    fun test(){
    }
}