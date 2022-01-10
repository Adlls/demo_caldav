package adls.demo_caldav.workflow.service.decorator_workflow_item.impl

import adls.demo_caldav.workflow.service.decorator_workflow_item.WorkFlowItemVODecorator
import adls.demo_caldav.workflow.vo.WorkflowItemVO

open class WorkflowItemVOBaseDecorator(
    private var workflowItemVO: WorkflowItemVO
): WorkFlowItemVODecorator {
    private var workflowItemVOWrapper: WorkflowItemVO = this.workflowItemVO


    private fun setMeta(workflowItemVO: WorkflowItemVO): WorkflowItemVO {
        /* Ставим мета инфу для workFlowItemVO */
        return workflowItemVOWrapper.apply {
            description = "wfsdfs"
        }
    }

    override fun setWorkflowItemVOProperties() {
      this.workflowItemVOWrapper = setMeta(workflowItemVO)
    }

    override fun getWorkflowItemVO(): WorkflowItemVO =
        this.workflowItemVOWrapper
}
