package adls.demo_caldav.calendar.repository

import adls.demo_caldav.calendar.entity.ImportedCalendar
import adls.demo_caldav.core.repository.BaseRepository
import adls.demo_caldav.user.entity.User
import org.springframework.stereotype.Repository

@Repository
interface ImportedCalendarRepository: BaseRepository<ImportedCalendar, Long> {
    fun existsByAuthTokenUserAndCaldavUrl(user: User, caldavUrl: String): Boolean
}
