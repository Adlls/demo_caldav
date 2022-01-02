package adls.demo_caldav.caldav.impl

import adls.demo_caldav.caldav.CaldavRequest
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.DateTime
import net.fortuna.ical4j.model.component.VEvent
import org.springframework.stereotype.Component

@Component
class CaldavRequestImpl(

): CaldavRequest {
    override fun getItemEventsByDates(start: DateTime, end: DateTime, caldavUrl: String): List<VEvent> {
        TODO("Not yet implemented")
    }

    override fun getCalendarByCaldavUrl(caldavUrl: String): Calendar {
        TODO("Not yet implemented")
    }
}
