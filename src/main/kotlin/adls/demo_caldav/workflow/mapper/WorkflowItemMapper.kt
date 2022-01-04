package adls.demo_caldav.workflow.mapper

import adls.demo_caldav.workflow.vo.WorkflowItemVO
import org.mapstruct.*

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
abstract class WorkflowItemMapper {

    abstract fun updatedFromWorkflowItemVO(
        workflowItemVO: WorkflowItemVO,
        @MappingTarget workflowItemVOTarget: WorkflowItemVO
    ): WorkflowItemVO
}
