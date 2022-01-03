package adls.demo_caldav.auth_token.service.impl

import adls.demo_caldav.auth_token.entity.AuthToken
import adls.demo_caldav.auth_token.repository.AuthTokenRepository
import adls.demo_caldav.auth_token.service.AuthTokenService
import adls.demo_caldav.core.service.impl.BaseServiceImpl
import org.springframework.stereotype.Service

@Service
class AuthTokenServiceImpl:
    BaseServiceImpl<Long, AuthToken, AuthTokenRepository>(),
    AuthTokenService {
}
