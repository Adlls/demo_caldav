package adls.demo_caldav.workflow.service.decorator_workflow_item.impl

import adls.demo_caldav.calendar.vo.VEventVO
import adls.demo_caldav.workflow.vo.WorkflowItemVO
import net.fortuna.ical4j.model.component.VEvent

class WorkflowItemVOMeetDecorator(
    private var workflowItemVO: WorkflowItemVO
) : WorkflowItemVOBaseDecorator(workflowItemVO) {
    private var workflowItemVOWrapper: WorkflowItemVO = this.workflowItemVO
    private lateinit var currentVEvent: VEvent


     override fun setWorkflowItemVOProperties() {
        this.workflowItemVOWrapper = setMeet(currentVEvent)
    }

    override fun getWorkflowItemVO(): WorkflowItemVO {
        return workflowItemVOWrapper
    }

    fun setVEvent(vEvent: VEvent) {
        this.currentVEvent = vEvent
    }

    private fun setMeet(vEvent: VEvent): WorkflowItemVO =
        setVEventToWorkflowItemVO(vEvent, this.workflowItemVOWrapper)

    private fun setVEventToWorkflowItemVO(
        vEvent: VEvent,
        sourceWorkflowItemVO: WorkflowItemVO
    ): WorkflowItemVO =
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
}
