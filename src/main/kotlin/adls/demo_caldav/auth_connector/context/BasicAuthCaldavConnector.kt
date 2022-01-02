package adls.demo_caldav.auth_connector.context

import adls.demo_caldav.auth_connector.AuthStragety
import adls.demo_caldav.auth_token.service.YandexTokenService

class BasicAuthCaldavConnector(
    private val yandexTokenService: YandexTokenService
) {
    fun processAuth(authStrategy: AuthStragety, userId: Long): Pair<String, String> =
        authStrategy.executeByAuthProtocol(getTokenByUserId(userId))

    private fun getTokenByUserId(userId: Long): String =
        yandexTokenService.getTokenCaldavByUserIdAndSourceTypeIsBasic(userId)
}
