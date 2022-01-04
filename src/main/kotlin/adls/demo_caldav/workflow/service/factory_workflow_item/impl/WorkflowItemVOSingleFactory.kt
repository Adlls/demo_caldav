package adls.demo_caldav.workflow.service.factory_workflow_item.impl

import adls.demo_caldav.workflow.mapper.VEventWorkflowItemMapper
import adls.demo_caldav.workflow.mapper.WorkflowItemMapper
import adls.demo_caldav.workflow.service.factory_workflow_item.WorkflowItemVOFactory
import adls.demo_caldav.workflow.vo.WorkflowItemVO
import net.fortuna.ical4j.model.component.VEvent
import org.springframework.stereotype.Component

@Component
class WorkflowItemVOSingleFactory(
    private val vEventWorkflowItemMapper: VEventWorkflowItemMapper,
    private val workflowItemMapper: WorkflowItemMapper
): WorkflowItemVOFactory {
    private lateinit var workflowItemVO: WorkflowItemVO
    private fun setMeta(sourceWorkflowItemVO: WorkflowItemVO): WorkflowItemVO {
        TODO("Not yet implemented")
    }

    private fun setMeet(sourceWorkflowItemVO: WorkflowItemVO, vEvent: VEvent): WorkflowItemVO =
        TODO("Not yet implemented")
 /*   workflowItemMapper
                .updatedFromWorkflowItemVO(
                    sourceWorkflowItemVO,
                    vEvent.let(vEventWorkflowItemMapper::toDto))*/

    private fun setTask(sourceWorkflowItemVO: WorkflowItemVO): WorkflowItemVO {
        TODO("Not yet implemented")
    }

    override fun getWorkflowItemVO(vEvent: VEvent): WorkflowItemVO {
        /*
        Декоратор?
        setMeta(this.workflowItemVO).let {
            setTask(setMeet(it, vEvent))
        }
         */
       return setMeet(this.workflowItemVO, vEvent)
    }
}
