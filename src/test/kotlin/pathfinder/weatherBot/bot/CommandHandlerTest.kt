package pathfinder.weatherBot.bot

import org.junit.jupiter.api.Test
import pathfinder.weatherBot.interaction.Client
import pathfinder.weatherBot.interaction.CommandHandler

internal class CommandHandlerTest{
    val commandHandler = CommandHandler(Client("bogus", "bogus"))

    @Test
    fun test(){
    }
}