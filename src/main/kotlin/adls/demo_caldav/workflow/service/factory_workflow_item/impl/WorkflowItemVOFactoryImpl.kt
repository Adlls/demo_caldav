package adls.demo_caldav.workflow.service.factory_workflow_item.impl

import adls.demo_caldav.workflow.service.decorator_workflow_item.impl.WorkflowItemVOMeetDecorator
import adls.demo_caldav.workflow.service.factory_workflow_item.WorkflowItemVOFactory
import adls.demo_caldav.workflow.vo.WorkflowItemVO
import net.fortuna.ical4j.model.component.VEvent
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*

@Component
class WorkflowItemVOFactoryImpl: WorkflowItemVOFactory {


    fun getRecurVEvents(
        vEvents: List<VEvent>,
        startDate: ZonedDateTime,
        endDate: ZonedDateTime): List<VEvent> {
        val listOfRecurVEvents = mutableListOf<VEvent>()
        vEvents.forEach {
            val recId = it.recurrenceId?.date
            if (recId != null) {
                if (recId.after(Date.from(startDate.toInstant())) &&
                    recId.before(Date.from(endDate.toInstant()))) {
                    listOfRecurVEvents.add(it)
                }
            }
        }
        return listOfRecurVEvents
    }

    override fun getWorkflowItemsVO(
        vEvents: List<VEvent>,
        startDate: ZonedDateTime,
        endDate: ZonedDateTime
        ): List<WorkflowItemVO> {
        val workflowItemVO = WorkflowItemVO()
        var listOfWorkflowItemsVO = mutableListOf<WorkflowItemVO>()
        val listOfRecurEvents = getRecurVEvents(vEvents, startDate, endDate)
        val vEventsOperations = vEvents.toMutableList().minus(listOfRecurEvents)
        vEventsOperations.forEach {
            WorkflowItemVOMeetDecorator(workflowItemVO).let { meetDecorator ->
                meetDecorator.setEventsProperties(it, startDate, endDate)
                listOfWorkflowItemsVO = listOfWorkflowItemsVO
                    .plus(meetDecorator.getWorkflowItemsVO(it, listOfRecurEvents))
                    .toMutableList()
            }
        }
        return listOfWorkflowItemsVO
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
