package adls.demo_caldav.workflow.service.decorator_workflow_item.impl

import adls.demo_caldav.workflow.vo.WorkflowItemVO

class WorkflowItemVOTaskDecorator(
    private var workflowItemVO: WorkflowItemVO,
) : WorkflowItemVOBaseDecorator(workflowItemVO) {
    private var workflowItemVOWrapper: WorkflowItemVO = this.workflowItemVO

    override fun getWorkflowItemVO(): WorkflowItemVO {
        return workflowItemVOWrapper
    }

    override fun setWorkflowItemVOProperties() {
        setTask(workflowItemVO).apply {
            description = "wfsdfs"
        }
    }

    private fun setTask(workflowItemVO: WorkflowItemVO): WorkflowItemVO {
        /* ставим инфу по таскам */
        return workflowItemVOWrapper
    }
}
