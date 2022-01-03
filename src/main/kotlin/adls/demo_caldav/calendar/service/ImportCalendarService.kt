package adls.demo_caldav.calendar.service

import adls.demo_caldav.calendar.entity.ImportedCalendar
import adls.demo_caldav.calendar.repository.ImportedCalendarRepository
import adls.demo_caldav.core.service.BaseService

interface ImportCalendarService: BaseService<Long, ImportedCalendar, ImportedCalendarRepository> {
    fun importCalDavByUserId(userId: Long, caldavUrl: String, login: String, password: String)
}
