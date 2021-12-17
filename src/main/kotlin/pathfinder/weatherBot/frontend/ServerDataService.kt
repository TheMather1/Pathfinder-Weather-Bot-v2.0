package pathfinder.weatherBot.frontend

import net.dv8tion.jda.api.JDA
import org.springframework.stereotype.Service

@Service
class ServerDataService(
    private val jda: JDA
) {
    fun getServersForUser(id: String) = jda.guilds.filter {
     it.loadMembers().get().any { it.id == id }
    }
}