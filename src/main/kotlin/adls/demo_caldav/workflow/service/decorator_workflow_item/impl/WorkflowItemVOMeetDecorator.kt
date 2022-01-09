package adls.demo_caldav.workflow.service.decorator_workflow_item.impl

import adls.demo_caldav.workflow.vo.WorkflowItemVO
import net.fortuna.ical4j.model.Date
import net.fortuna.ical4j.model.DateTime
import net.fortuna.ical4j.model.Property
import net.fortuna.ical4j.model.component.VEvent
import net.fortuna.ical4j.model.parameter.Value
import net.fortuna.ical4j.model.property.ExDate
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

    fun getWorkflowItemsVO(
        vEvent: VEvent,
        recurVEvents: List<VEvent>
    ): List<WorkflowItemVO> {
        var listOfWorkflowItemVO = mutableListOf<WorkflowItemVO>()
        return if (vEvent.getProperty<RRule>(Property.RRULE)?.recur != null) {
            listOfWorkflowItemVO = listOfWorkflowItemVO
                .plus(getRecurrenceEvents(vEvent, recurVEvents))
                .toMutableList()
            return listOfWorkflowItemVO
        } else {
            listOfWorkflowItemVO.add(setVEventToWorkflowItemVO(
                vEvent,
                this.workflowItemVOWrapper))
            listOfWorkflowItemVO
        }
    }

    private fun setRecurEventToWorkflowItemVO(
        replacementEvent: VEvent?,
        targetEvent: VEvent,
        targetDate: Date
    ): WorkflowItemVO? {
       val setEvent = replacementEvent ?: targetEvent
       val targetStartDate = if (replacementEvent != null) {
           replacementEvent.startDate.date.toInstant().atZone(
               replacementEvent.startDate.timeZone.toZoneId()
           ).withFixedOffsetZone()
       } else {
           targetDate.toInstant().atZone(
               setEvent.startDate.timeZone.toZoneId()
           )
               .withFixedOffsetZone()
               .withHour(setEvent.startDate.date.hours)
               .withMinute(setEvent.startDate.date.minutes)
               .withSecond(setEvent.startDate.date.seconds)
       }
        if (setEvent
                .getProperty<ExDate>(Property.EXDATE)
                ?.dates?.contains(DateTime(Date.from(targetStartDate.toInstant()))) == true
        ) {
            return null
        }

        val targetEndDate = if (replacementEvent != null) {
            replacementEvent.endDate.date.toInstant().atZone(
                replacementEvent.endDate.timeZone.toZoneId()
            ).withFixedOffsetZone()
        } else {
            targetDate.toInstant().atZone(
                setEvent.endDate.timeZone.toZoneId()
            )
                .withFixedOffsetZone()
                .withHour(setEvent.endDate.date.hours)
                .withMinute(setEvent.endDate.date.minutes)
                .withSecond(setEvent.endDate.date.seconds)
        }
       return WorkflowItemVO(
            dateSpentStart = targetStartDate,
            dateSpentEnd = targetEndDate,
            title = setEvent.summary?.value,
            description = setEvent.description?.value
        )
    }

    private fun getRecurrenceEvents(
        vEvent: VEvent,
        recurVEvents: List<VEvent>
    ): List<WorkflowItemVO> {
        val vEventWithRules = vEvent.getProperty<RRule>(Property.RRULE)?.recur
        val listOfWorkflowItemVO = mutableListOf<WorkflowItemVO>()
        return if (vEventWithRules != null) {
            val dateList = vEventWithRules.getDates(
                Date(checkNotNull(this.startDate.toInstant()?.toEpochMilli())),
                Date(checkNotNull(this.endDate.toInstant()?.toEpochMilli())),
                Value.DATE_TIME
            )
            dateList.forEach {
                val replacementEvent = recurVEvents.find { recurEvent ->
                    recurEvent.uid.value == vEvent.uid.value
                            && recurEvent.recurrenceId.date.toInstant().atZone(
                        vEvent.startDate.timeZone.toZoneId()
                    ).withFixedOffsetZone()
                        .withHour(0)
                        .withMinute(0)
                        .withSecond(0) == it.toInstant().atZone(
                        vEvent.startDate.timeZone.toZoneId()
                    ).withFixedOffsetZone()
                        .withHour(0)
                }
                setRecurEventToWorkflowItemVO(replacementEvent, vEvent, it)?.let { workflow ->
                    listOfWorkflowItemVO.add(workflow)
                }
            }
            listOfWorkflowItemVO

        } else {
            listOfWorkflowItemVO
        }
    }
}
