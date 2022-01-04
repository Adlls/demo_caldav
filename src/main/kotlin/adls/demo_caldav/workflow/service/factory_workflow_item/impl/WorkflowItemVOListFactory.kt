package adls.demo_caldav.workflow.service.factory_workflow_item.impl

import adls.demo_caldav.workflow.service.factory_workflow_item.WorkflowItemVOFactory
import adls.demo_caldav.workflow.vo.WorkflowItemVO
import net.fortuna.ical4j.model.component.VEvent

class WorkflowItemVOListFactory: WorkflowItemVOFactory {

    override fun getWorkflowItemVO(vEvent: VEvent): WorkflowItemVO {
        TODO("Not yet implemented")
    }
}
