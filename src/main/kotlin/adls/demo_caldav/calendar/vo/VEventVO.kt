package adls.demo_caldav.calendar.vo

import java.time.ZonedDateTime

data class VEventVO(
    var title: String? = null,
    var description: String? = null,
    var dateSpentStart: ZonedDateTime? = null,
    var dateSpentEnd: ZonedDateTime? = null,

)
