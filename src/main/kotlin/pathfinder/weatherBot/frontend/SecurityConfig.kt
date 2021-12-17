package pathfinder.weatherBot.frontend

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange


@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    private val restTemplate = RestTemplate()

    override fun configure(http: HttpSecurity) {
        http.run {
            authorizeRequests()
                .antMatchers("/portal/**").authenticated()
                .antMatchers("/").permitAll()
            oauth2Login()
                .tokenEndpoint()//.accessTokenResponseClient(::getTokenResponse)
                .and().userInfoEndpoint().userService(::loadUser)
            logout().logoutUrl("/logout").logoutSuccessUrl("/")
        }
    }

//    val accessTokenResponseClient = DefaultAuthorizationCodeTokenResponseClient().apply {
//        this.getTokenResponse()
//    }

    fun getTokenResponse(authorizationGrantRequest: OAuth2AuthorizationCodeGrantRequest): OAuth2AccessTokenResponse {
        return authorizationGrantRequest.clientRegistration.run {
            restTemplate.exchange<OAuth2AccessTokenResponse>(
                providerDetails.tokenUri,
                HttpMethod.POST,
                HttpEntity(
                    LinkedMultiValueMap<String, String>().apply {
                        add("client_id", clientId)
                        add("client_secret", clientSecret)
                        add("grant_type", authorizationGrantType.value)
                        add("code", authorizationGrantRequest.authorizationExchange.authorizationResponse.code)
                        add(
                            "redirect_uri",
                            authorizationGrantRequest.authorizationExchange.authorizationRequest.redirectUri
                        )
                        add("scope", java.lang.String.join(" ", scopes))
                    },
                    HttpHeaders().apply {
                        contentType = MediaType.APPLICATION_FORM_URLENCODED
                        add(HttpHeaders.USER_AGENT, "WeatherBot")
                    }
                )
            ).body!!//.run {
//                OAuth2AccessTokenResponse.withToken(get("access_token"))
//                    .tokenType(OAuth2AccessToken.TokenType.BEARER)
//                    .expiresIn(get("expires_in")!!.toLong())
//                    .scopes(get("scope")!!.split("\\s+").toSet().ifEmpty { authorizationGrantRequest.authorizationExchange.authorizationRequest.scopes })
//                    .build()
//            }
        }
    }

    fun loadUser(userRequest: OAuth2UserRequest) = restTemplate.exchange<Map<String, String>>(
        userRequest.clientRegistration.providerDetails.userInfoEndpoint.uri,
        HttpMethod.GET,
        HttpEntity<Any>(HttpHeaders().apply {
            setBearerAuth(userRequest.accessToken.tokenValue)
            set(HttpHeaders.USER_AGENT, "WeatherBot")
        })
    ).body?.let { DiscordUser(it) }

}