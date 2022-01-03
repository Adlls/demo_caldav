package adls.demo_caldav.workflow.service

import adls.demo_caldav.core.service.BaseService
import adls.demo_caldav.workflow.entity.WorkflowItem
import adls.demo_caldav.workflow.repository.WorkflowItemRepository
import adls.demo_caldav.workflow.vo.WorkflowItemVO
import java.time.ZonedDateTime

interface WorkflowService: BaseService<Long, WorkflowItem, WorkflowItemRepository> {
    fun getWorkflowItemsBetweenDates(startDate: ZonedDateTime, endDate: ZonedDateTime): List<WorkflowItemVO>
}
