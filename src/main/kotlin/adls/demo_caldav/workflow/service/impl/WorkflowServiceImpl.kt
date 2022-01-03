package adls.demo_caldav.workflow.service.impl

import adls.demo_caldav.core.service.impl.BaseServiceImpl
import adls.demo_caldav.workflow.entity.WorkflowItem
import adls.demo_caldav.workflow.repository.WorkflowItemRepository
import adls.demo_caldav.workflow.service.WorkflowService
import adls.demo_caldav.workflow.vo.WorkflowItemVO
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class WorkflowServiceImpl:
    BaseServiceImpl<Long, WorkflowItem, WorkflowItemRepository>(),
    WorkflowService {
    override fun getWorkflowItemsBetweenDates(startDate: ZonedDateTime, endDate: ZonedDateTime): List<WorkflowItemVO> {
        TODO("Not yet implemented")
    }
}
