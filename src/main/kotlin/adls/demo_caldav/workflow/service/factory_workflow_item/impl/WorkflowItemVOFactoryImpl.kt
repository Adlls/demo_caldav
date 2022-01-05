package adls.demo_caldav.workflow.service.factory_workflow_item.impl

import adls.demo_caldav.workflow.service.decorator_workflow_item.impl.WorkflowItemVOMeetDecorator
import adls.demo_caldav.workflow.service.decorator_workflow_item.impl.WorkflowItemVOTaskDecorator
import adls.demo_caldav.workflow.service.factory_workflow_item.WorkflowItemVOFactory
import adls.demo_caldav.workflow.vo.WorkflowItemVO
import net.fortuna.ical4j.model.component.VEvent
import org.springframework.stereotype.Component

@Component
class WorkflowItemVOFactoryImpl: WorkflowItemVOFactory {


    override fun getWorkflowItemVO(vEvent: VEvent): WorkflowItemVO {
        val workflowItemVO = WorkflowItemVO()
        return WorkflowItemVOTaskDecorator(
            workflowItemVO
        ).let { taskDecorator ->
            taskDecorator.setWorkflowItemVOProperties()
            taskDecorator.getWorkflowItemVO()
        }.also {
            WorkflowItemVOMeetDecorator(it).let { meetDecorator ->
                meetDecorator.setVEvent(vEvent)
                meetDecorator.setWorkflowItemVOProperties()
                meetDecorator.getWorkflowItemVO()
            }
        }
    }
}
