package pathfinder.weatherBot.frontend

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Suppress("SpringMVCViewInspection") //IntelliJ fails to detect .jsp files in deployment folder.
@Controller
@RequestMapping("/")
class LandingPageController {

    @GetMapping
    fun landingPage() = "index"
}
