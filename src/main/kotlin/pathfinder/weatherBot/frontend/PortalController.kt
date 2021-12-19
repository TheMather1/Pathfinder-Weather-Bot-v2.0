package pathfinder.weatherBot.frontend

import net.dv8tion.jda.api.JDA
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/portal")
class PortalController(private val jda: JDA) {

    @GetMapping
    fun viewPortal(model: Model, @AuthenticationPrincipal user: DiscordUser): String {
        val servers = user.mutualGuilds
        model.addAttribute("servers", servers.associate { it.name to it.idLong })
        return "portal"
    }
}