package adls.demo_caldav.auth_token.service

import adls.demo_caldav.auth_token.entity.AuthToken
import adls.demo_caldav.auth_token.repository.AuthTokenRepository
import adls.demo_caldav.core.service.BaseService

interface YandexTokenService: BaseService<Long, AuthToken, AuthTokenRepository> {
    fun getTokenCaldavByUserIdAndSourceTypeIsBasic(userId: Long): String
}
