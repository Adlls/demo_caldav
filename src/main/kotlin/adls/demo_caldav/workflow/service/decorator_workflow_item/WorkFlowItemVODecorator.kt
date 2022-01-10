package adls.demo_caldav.workflow.service.decorator_workflow_item

import adls.demo_caldav.workflow.vo.WorkflowItemVO

interface WorkFlowItemVODecorator {
    fun setWorkflowItemVOProperties()
    fun getWorkflowItemVO(): WorkflowItemVO
}
