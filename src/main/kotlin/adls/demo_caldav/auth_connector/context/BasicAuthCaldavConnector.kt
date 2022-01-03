package adls.demo_caldav.auth_connector.context

import adls.demo_caldav.auth_connector.AuthStrategy
import adls.demo_caldav.auth_token.service.YandexTokenService
import org.springframework.stereotype.Component

@Component
class BasicAuthCaldavConnector(
    private val yandexTokenService: YandexTokenService
) {
    fun processAuth(authStrategy: AuthStrategy, userId: Long): Pair<String, String> =
        authStrategy.executeByAuthProtocol(getTokenByUserId(userId))

    private fun getTokenByUserId(userId: Long): String =
        yandexTokenService.getTokenCaldavByUserIdAndSourceTypeIsBasic(userId)
}
