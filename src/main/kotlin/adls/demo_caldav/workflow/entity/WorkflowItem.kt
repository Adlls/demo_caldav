package adls.demo_caldav.workflow.entity

import adls.demo_caldav.core.entity.BaseEntity
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "workflow_item")
data class WorkflowItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "date_spent_start")
    var dateSpentStart: ZonedDateTime? = null,
    @Column(name = "date_spent_end")
    var dateSpentEnd: ZonedDateTime? = null,
    var title: String? = null,
    var description: String? = null
): BaseEntity()
