package adls.demo_caldav.caldav

import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.DateTime
import net.fortuna.ical4j.model.component.VEvent

interface CaldavRequest {
    fun getItemEventsByDates(
        start: DateTime,
        end: DateTime,
        caldavUrl: String
    ): List<VEvent>

    fun getCalendarByCaldavUrl(caldavUrl: String): Calendar
}
