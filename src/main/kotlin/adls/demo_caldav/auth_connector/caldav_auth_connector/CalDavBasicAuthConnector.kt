package adls.demo_caldav.auth_connector.caldav_auth_connector

import adls.demo_caldav.auth_connector.AuthStragety

class CalDavBasicAuthConnector(
    private val authStrategy: AuthStragety
) {
    fun processAuth(authStrategy: AuthStragety, userId: String): Pair<String, String> =
        authStrategy.executeByAuthProtocol(getTokenByUserId(userId))

    private fun getTokenByUserId(userId: String): String = ""
}
