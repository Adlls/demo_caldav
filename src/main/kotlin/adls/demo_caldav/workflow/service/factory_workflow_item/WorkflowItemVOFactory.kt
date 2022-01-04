package adls.demo_caldav.workflow.service.factory_workflow_item

import adls.demo_caldav.workflow.vo.WorkflowItemVO
import net.fortuna.ical4j.model.component.VEvent

interface WorkflowItemVOFactory {
    fun getWorkflowItemVO(vEvent: VEvent): WorkflowItemVO
}
