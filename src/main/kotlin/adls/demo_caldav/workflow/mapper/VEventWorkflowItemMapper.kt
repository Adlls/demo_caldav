package adls.demo_caldav.workflow.mapper

import adls.demo_caldav.workflow.vo.WorkflowItemVO
import net.fortuna.ical4j.model.component.VEvent
import org.mapstruct.*

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
abstract class VEventWorkflowItemMapper {
   /* @Mappings(
        Mapping(target = "id", ignore = true)
    )
    abstract fun toDto(entity: VEvent): WorkflowItemVO*/
}
