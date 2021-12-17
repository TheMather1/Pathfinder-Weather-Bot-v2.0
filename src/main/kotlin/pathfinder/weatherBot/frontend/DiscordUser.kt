package pathfinder.weatherBot.frontend

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority

class DiscordUser(
    private val attributes: Map<String, String>
) : OAuth2User {
    val id = attributes["id"]!!

    override fun getName() = attributes["username"]!!

    override fun getAttributes() = attributes

    override fun getAuthorities() = setOf<GrantedAuthority>(OAuth2UserAuthority(attributes))
}