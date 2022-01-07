package adls.demo_caldav.workflow.service.decorator_workflow_item.impl

import adls.demo_caldav.workflow.vo.WorkflowItemVO
import net.fortuna.ical4j.model.Date
import net.fortuna.ical4j.model.Property
import net.fortuna.ical4j.model.component.VEvent
import net.fortuna.ical4j.model.parameter.Value
import net.fortuna.ical4j.model.property.RRule
import java.time.ZonedDateTime

class WorkflowItemVOMeetDecorator(
    private var workflowItemVO: WorkflowItemVO
) : WorkflowItemVOBaseDecorator(workflowItemVO) {
    private var workflowItemVOWrapper: WorkflowItemVO = this.workflowItemVO
    private lateinit var currentVEvent: VEvent
    private lateinit var startDate: ZonedDateTime
    private lateinit var endDate: ZonedDateTime


     override fun setWorkflowItemVOProperties() {
        this.workflowItemVOWrapper = setMeet(currentVEvent)
    }

    override fun getWorkflowItemVO(): WorkflowItemVO {
        return workflowItemVOWrapper
    }

    fun setEventsProperties(
        vEvent: VEvent,
        startDate: ZonedDateTime,
        endDate: ZonedDateTime
    ) {
        this.currentVEvent = vEvent
        this.startDate = startDate
        this.endDate = endDate
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

    fun getWorkflowItemsVO(vEvent: VEvent): List<WorkflowItemVO> {
        var listOfWorkflowItemVO = mutableListOf<WorkflowItemVO>()
        return if (vEvent.getProperty<RRule>(Property.RRULE)?.recur != null) {
            listOfWorkflowItemVO = listOfWorkflowItemVO
                .plus(getRecurrenceEvents(vEvent))
                .toMutableList()
            return listOfWorkflowItemVO
        } else {
            listOfWorkflowItemVO.add(setVEventToWorkflowItemVO(
                vEvent,
                this.workflowItemVOWrapper))
            listOfWorkflowItemVO
        }
    }

    private fun getRecurrenceEvents(vEvent: VEvent): List<WorkflowItemVO> {
        val vEventWithRules = vEvent.getProperty<RRule>(Property.RRULE)?.recur
        val listOfWorkflowItemVO = mutableListOf<WorkflowItemVO>()
        return if (vEventWithRules != null) {
            val dateList = vEventWithRules.getDates(
                Date(checkNotNull(this.startDate.toInstant()?.toEpochMilli())),
                Date(checkNotNull(this.endDate.toInstant()?.toEpochMilli())),
                Value.DATE_TIME
            )
            dateList.forEach {
                listOfWorkflowItemVO.add(
                    WorkflowItemVO(
                        dateSpentStart = it.toInstant().atZone(
                            vEvent.startDate.timeZone.toZoneId()
                        )
                            .withFixedOffsetZone()
                            .withHour(vEvent.startDate.date.hours)
                            .withMinute(vEvent.startDate.date.minutes)
                            .withSecond(vEvent.startDate.date.seconds),
                        dateSpentEnd = it.toInstant().atZone(
                            vEvent.endDate.timeZone.toZoneId()
                        )
                            .withFixedOffsetZone()
                            .withHour(vEvent.endDate.date.hours)
                            .withMinute(vEvent.endDate.date.minutes)
                            .withSecond(vEvent.endDate.date.seconds),
                        title = vEvent.summary?.value,
                        description = vEvent.description?.value
                    )
                )
            }
            listOfWorkflowItemVO

        } else {
            listOfWorkflowItemVO
        }
    }
}
