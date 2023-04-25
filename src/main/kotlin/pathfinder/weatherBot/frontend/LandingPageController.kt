package pathfinder.weatherBot.frontend

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Suppress("SameReturnValue", "SameReturnValue", "SameReturnValue", "SameReturnValue", "SameReturnValue",
    "SameReturnValue", "SameReturnValue", "SameReturnValue"
)
@Controller
@RequestMapping("/")
class LandingPageController {

    @GetMapping
    fun landingPage() = "index"
}
