package adls.demo_caldav.workflow.mapper

import adls.demo_caldav.workflow.vo.WorkflowItemVO
import net.fortuna.ical4j.model.component.VEvent
import org.mapstruct.*
import java.time.ZonedDateTime

@Mapper(
    componentModel = "spring",
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
abstract class VEventMapper { }
