package adls.demo_caldav.workflow.controller

import adls.demo_caldav.workflow.service.WorkflowService
import adls.demo_caldav.workflow.vo.WorkflowItemVO
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime

@RestController
@Validated
@RequestMapping("v1/workflow")
class WorkflowController(
    private val workflowService: WorkflowService
) {

    @GetMapping("{userId}")
    fun getWorkflowItemsByDates(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @RequestParam startDate: ZonedDateTime,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @RequestParam endDate: ZonedDateTime,
        @PathVariable userId: Long): List<WorkflowItemVO> =
        workflowService.getWorkflowItemsBetweenDates(startDate, endDate, userId)
}
