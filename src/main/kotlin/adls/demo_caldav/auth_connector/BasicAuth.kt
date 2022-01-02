package adls.demo_caldav.auth_connector

class BasicAuth : AuthStragety {
    override fun executeByAuthProtocol(token: String): Pair<String, String> =
        Pair(
            "Authorization",
            "Basic $token"
        )
}
