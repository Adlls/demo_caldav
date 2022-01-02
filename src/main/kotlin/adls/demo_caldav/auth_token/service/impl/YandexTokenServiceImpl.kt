package adls.demo_caldav.auth_token.service.impl

import adls.demo_caldav.auth_token.entity.AuthToken
import adls.demo_caldav.auth_token.repository.AuthTokenRepository
import adls.demo_caldav.auth_token.service.YandexTokenService
import adls.demo_caldav.core.service.impl.BaseServiceImpl
import org.springframework.stereotype.Service

@Service
class YandexTokenServiceImpl :
    BaseServiceImpl<Long, AuthToken, AuthTokenRepository>(),
    YandexTokenService {

    override fun getTokenCaldavByUserIdAndSourceTypeIsBasic(userId: Long): String =
        repository.findTokenCalDavBySourceTypeIsYandexAndAuthTypeIsBasic(userId)
}
