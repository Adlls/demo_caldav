package adls.demo_caldav.workflow.service.factory_workflow_item.impl

import adls.demo_caldav.workflow.service.decorator_workflow_item.impl.WorkflowItemVOMeetDecorator
import adls.demo_caldav.workflow.service.decorator_workflow_item.impl.WorkflowItemVOTaskDecorator
import adls.demo_caldav.workflow.service.factory_workflow_item.WorkflowItemVOFactory
import adls.demo_caldav.workflow.vo.WorkflowItemVO
import net.fortuna.ical4j.model.component.VEvent
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class WorkflowItemVOFactoryImpl: WorkflowItemVOFactory {


    override fun getWorkflowItemsVO(
        vEvent: VEvent,
        startDate: ZonedDateTime,
        endDate: ZonedDateTime
    ): List<WorkflowItemVO> {
        val workflowItemVO = WorkflowItemVO()
        return WorkflowItemVOMeetDecorator(workflowItemVO).let { meetDecorator ->
            meetDecorator.setEventsProperties(vEvent, startDate, endDate)
            meetDecorator.getWorkflowItemsVO(vEvent)
        }
        /*
        return WorkflowItemVOTaskDecorator(
            workflowItemVO
        ).let { taskDecorator ->
            taskDecorator.setWorkflowItemVOProperties()
            taskDecorator.getWorkflowItemVO()
        }.also {
            WorkflowItemVOMeetDecorator(it).let { meetDecorator ->
                meetDecorator.setEventsProperties(vEvent, startDate, endDate)
                meetDecorator.setWorkflowItemVOProperties()
                meetDecorator.getWorkflowItemsVO(vEvent)
            }
        }
        */
    }
}
