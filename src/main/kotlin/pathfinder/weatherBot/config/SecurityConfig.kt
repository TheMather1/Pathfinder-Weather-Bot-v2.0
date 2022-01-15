package pathfinder.weatherBot.config

import net.dv8tion.jda.api.JDA
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import pathfinder.weatherBot.frontend.DiscordUser


@Configuration
@EnableWebSecurity
class SecurityConfig(
    val jda: JDA
) : WebSecurityConfigurerAdapter() {

    private val restTemplate = RestTemplate()

    override fun configure(http: HttpSecurity) {
        http.run {
            cors().and().csrf().disable()
            authorizeRequests()
                .antMatchers("/portal/**").authenticated()
                .anyRequest().permitAll()
            oauth2Login()
                .tokenEndpoint()
                .and().userInfoEndpoint().userService(::loadUser)
            logout().logoutUrl("/logout").logoutSuccessUrl("/")
        }
    }

    fun loadUser(userRequest: OAuth2UserRequest) = restTemplate.exchange<Map<String, String>>(
        userRequest.clientRegistration.providerDetails.userInfoEndpoint.uri,
        HttpMethod.GET,
        HttpEntity<Any>(HttpHeaders().apply {
            setBearerAuth(userRequest.accessToken.tokenValue)
            set(HttpHeaders.USER_AGENT, "WeatherBot")
        })
    ).body?.let { DiscordUser(it, jda) }

}
