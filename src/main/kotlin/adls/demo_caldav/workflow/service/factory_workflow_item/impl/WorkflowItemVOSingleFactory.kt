package adls.demo_caldav.workflow.service.factory_workflow_item.impl

import adls.demo_caldav.workflow.service.factory_workflow_item.WorkflowItemVOFactory
import adls.demo_caldav.workflow.vo.WorkflowItemVO
import net.fortuna.ical4j.model.component.VEvent
import org.springframework.stereotype.Component

@Component
class WorkflowItemVOSingleFactory: WorkflowItemVOFactory {
    private fun setMeta(sourceWorkflowItemVO: WorkflowItemVO): WorkflowItemVO {
        TODO("Not yet implemented")
    }

    private fun setMeet(vEvent: VEvent): WorkflowItemVO =
        setVEventToWorkflowItemVO(vEvent, WorkflowItemVO())

    private fun setTask(sourceWorkflowItemVO: WorkflowItemVO): WorkflowItemVO {
        TODO("Not yet implemented")
    }

    private fun setVEventToWorkflowItemVO(vEvent: VEvent, sourceWorkflowItemVO: WorkflowItemVO): WorkflowItemVO =
        sourceWorkflowItemVO.apply {
            this.title = vEvent.summary?.value
            this.description = vEvent.description?.value
            this.dateSpentStart = vEvent.startDate.date.toInstant().atZone(
                vEvent.startDate.timeZone.toZoneId()
            ).withFixedOffsetZone()
            this.dateSpentEnd = vEvent.endDate.date.toInstant().atZone(
                vEvent.endDate.timeZone.toZoneId()
            ).withFixedOffsetZone()
        }


    override fun getWorkflowItemVO(vEvent: VEvent): WorkflowItemVO {
        /*
        Декоратор?
        setMeta(this.workflowItemVO).let {
            setTask(setMeet(it, vEvent))
        }
         */
       return setMeet(vEvent)
    }
}
