package adls.demo_caldav.workflow.controller

import adls.demo_caldav.workflow.service.WorkflowService
import adls.demo_caldav.workflow.vo.WorkflowItemVO
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime

@RestController
@Validated
@RequestMapping("v1/workflow")
class WorkflowController(
    private val workflowService: WorkflowService
) {

    @GetMapping
    fun getWorkflowItemsByDates(
        startDate: ZonedDateTime,
        endDate: ZonedDateTime): List<WorkflowItemVO> =
        workflowService.getWorkflowItemsBetweenDates(startDate, endDate)
}
