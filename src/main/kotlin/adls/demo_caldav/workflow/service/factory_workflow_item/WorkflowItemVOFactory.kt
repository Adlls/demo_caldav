package adls.demo_caldav.workflow.service.factory_workflow_item

import adls.demo_caldav.workflow.vo.WorkflowItemVO
import net.fortuna.ical4j.model.component.VEvent
import java.time.ZonedDateTime

interface WorkflowItemVOFactory {
    fun getWorkflowItemsVO(
        vEvent: VEvent,
        startDate: ZonedDateTime,
        endDate: ZonedDateTime): List<WorkflowItemVO>
}
