package adls.demo_caldav.auth_connector

interface AuthStrategy {
    // TODO("Get Http header attributes by concrete release auth protocol")
    fun executeByAuthProtocol(token: String): Pair<String, String>
}
