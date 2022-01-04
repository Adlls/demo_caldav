package adls.demo_caldav.calendar.service.impl

import adls.demo_caldav.auth_token.entity.AuthServiceType
import adls.demo_caldav.auth_token.entity.AuthToken
import adls.demo_caldav.auth_token.entity.AuthType
import adls.demo_caldav.auth_token.entity.SourceType
import adls.demo_caldav.auth_token.service.AuthTokenService
import adls.demo_caldav.calendar.entity.ImportedCalendar
import adls.demo_caldav.calendar.repository.ImportedCalendarRepository
import adls.demo_caldav.calendar.service.ImportCalendarService
import adls.demo_caldav.core.service.impl.BaseServiceImpl
import adls.demo_caldav.user.entity.User
import adls.demo_caldav.user.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ImportCalendarServiceImpl(
    private val userService: UserService,
    private val authTokenService: AuthTokenService
) : BaseServiceImpl<Long, ImportedCalendar, ImportedCalendarRepository>(),
    ImportCalendarService {

    @Transactional
    override fun importCalDavByUserId(
        userId: Long,
        caldavUrl: String,
        login: String,
        password: String
    ) {
        val currentUser = userService.findByIdOrThrow(userId)
        require(!repository.existsByAuthTokenUserAndCaldavUrl(currentUser, caldavUrl)) {
            "CalDav url already exist for user id $userId"
        }
        initImportedCalendar(currentUser, caldavUrl, login, password)
    }

    override fun findImportedCalendarByUserIdAndCalDavUrl(userId: Long, caldavUrl: String): ImportedCalendar =
        repository.findByAuthTokenUserIdAndCaldavUrl(userId, caldavUrl)

    override fun findAllImportedCalendarByUserId(userId: Long): List<ImportedCalendar> =
        repository.findAllByAuthTokenUserId(userId)

    private fun initImportedCalendar(
        user: User,
        caldavUrl: String,
        login: String,
        password: String
    ) {
        authTokenService.save(
            AuthToken(
                token = Base64
                    .getEncoder()
                    .encodeToString("$login:$password".encodeToByteArray()),
                authType = AuthType.BASIC,
                sourceType = SourceType.YANDEX,
                externalServiceType = AuthServiceType.CALDAV,
                user = user
            )
        ).also { authToken ->
            save(
                ImportedCalendar(
                    caldavUrl = caldavUrl,
                    authToken = authToken
                )
            )
        }
    }
}
