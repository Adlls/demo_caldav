package adls.demo_caldav.auth_connector.context

import adls.demo_caldav.auth_connector.AuthStrategy
import adls.demo_caldav.auth_token.service.AuthTokenService
import org.springframework.stereotype.Component

@Component
class BasicAuthCaldavConnector(
    private val authTokenService: AuthTokenService
) {
    fun processAuth(authStrategy: AuthStrategy, userId: Long): Pair<String, String> =
        authStrategy.executeByAuthProtocol(getTokenByUserId(userId))

    private fun getTokenByUserId(userId: Long): String =
        authTokenService.getTokenCaldavByUserIdAndSourceTypeIsBasic(userId)
}
