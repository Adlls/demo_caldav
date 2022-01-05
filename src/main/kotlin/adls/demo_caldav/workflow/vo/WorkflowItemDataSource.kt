package adls.demo_caldav.workflow.vo

import net.fortuna.ical4j.model.component.VEvent

data class WorkflowItemDataSource(
    var meet: VEvent? = null,
    var task: Any? = null,
    var meta: Any? = null
)
