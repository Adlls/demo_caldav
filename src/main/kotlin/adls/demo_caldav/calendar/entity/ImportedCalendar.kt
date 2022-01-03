package adls.demo_caldav.calendar.entity

import adls.demo_caldav.core.entity.BaseEntity
import adls.demo_caldav.user.entity.User
import javax.persistence.*

@Entity
@Table(name = "imported_calendar")
data class ImportedCalendar(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "caldav_url")
    var caldavUrl: String? = null,
    @OneToOne
    @JoinColumn(name = "user_id")
    var user: User? = null
): BaseEntity()
