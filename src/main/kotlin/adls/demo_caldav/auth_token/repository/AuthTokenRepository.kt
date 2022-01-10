package adls.demo_caldav.auth_token.repository

import adls.demo_caldav.auth_token.entity.AuthToken
import adls.demo_caldav.auth_token.entity.SourceType
import adls.demo_caldav.core.repository.BaseRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface AuthTokenRepository: BaseRepository<AuthToken, Long> {

    @Query(
        """
            select at.token from AuthToken at 
                where at.authType = adls.demo_caldav.auth_token.entity.AuthType.BASIC 
                    and at.sourceType = adls.demo_caldav.auth_token.entity.SourceType.YANDEX
                    and at.externalServiceType = adls.demo_caldav.auth_token.entity.AuthServiceType.CALDAV
                    and at.user.id = ?1
        """
    )
    fun findTokenCalDavBySourceTypeIsYandexAndAuthTypeIsBasic(userId: Long,): String
}
