package adls.demo_caldav.workflow.repository

import adls.demo_caldav.core.repository.BaseRepository
import adls.demo_caldav.workflow.entity.WorkflowItem

interface WorkflowItemRepository: BaseRepository<WorkflowItem, Long>
