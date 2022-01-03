package adls.demo_caldav.workflow.vo

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZonedDateTime

data class WorkflowItemVO(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.msZ")
    var dateSpentStart: ZonedDateTime? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.msZ")
    var dateSpentEnd: ZonedDateTime? = null,
    var title: String? = null,
    var description: String? = null
)
