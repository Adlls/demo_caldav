package adls.demo_caldav.caldav

import adls.demo_caldav.auth_token.entity.AuthToken
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.DateTime
import net.fortuna.ical4j.model.component.VEvent

interface CaldavRequest {
    fun getItemEventsByDates(
        start: DateTime,
        end: DateTime,
        authToken: AuthToken,
        caldavUrl: String
    ): List<VEvent>

    fun getCalendarByCaldavUrl(caldavUrl: String, authToken: AuthToken): Calendar
}
