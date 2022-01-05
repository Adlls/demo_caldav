package adls.demo_caldav.workflow.service.impl

import adls.demo_caldav.caldav.CaldavRequest
import adls.demo_caldav.calendar.entity.ImportedCalendar
import adls.demo_caldav.calendar.service.ImportCalendarService
import adls.demo_caldav.core.service.impl.BaseServiceImpl
import adls.demo_caldav.workflow.entity.WorkflowItem
import adls.demo_caldav.workflow.repository.WorkflowItemRepository
import adls.demo_caldav.workflow.service.WorkflowService
import adls.demo_caldav.workflow.service.factory_workflow_item.WorkflowItemVOFactory
import adls.demo_caldav.workflow.service.factory_workflow_item.impl.WorkflowItemVOFactoryImpl
import adls.demo_caldav.workflow.vo.WorkflowItemVO
import net.fortuna.ical4j.model.DateTime
import net.fortuna.ical4j.model.component.VEvent
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.*

@Service
class WorkflowServiceImpl(
    private val caldavRequest: CaldavRequest,
    private val workflowItemVOFactory: WorkflowItemVOFactory,
    private val importCalendarService: ImportCalendarService
):
    BaseServiceImpl<Long, WorkflowItem, WorkflowItemRepository>(),
    WorkflowService {
    override fun getWorkflowItemsBetweenDates(
        startDate: ZonedDateTime,
        endDate: ZonedDateTime,
        userId: Long
    ): List<WorkflowItemVO> {
        val currentImportedCalendars =
            importCalendarService
                .findAllImportedCalendarByUserId(userId)
        return initVEvents(
            DateTime(Date.from(startDate.toInstant())),
            DateTime(Date.from(endDate.toInstant())),
            currentImportedCalendars)
    }

    private fun initVEvents(
        startDate: DateTime,
        endDate: DateTime,
        importedCalendars: List<ImportedCalendar>
    ): List<WorkflowItemVO> {
        var vEvents = listOf<VEvent>()
        importedCalendars.forEach {
            vEvents = vEvents.plus(
                caldavRequest.getItemEventsByDates(
                    startDate,
                    endDate,
                    checkNotNull(it.authToken),
                    checkNotNull(it.caldavUrl)
                )
            )
        }
       return vEvents.map {
            workflowItemVOFactory.getWorkflowItemVO(it)
        }
    }
}
