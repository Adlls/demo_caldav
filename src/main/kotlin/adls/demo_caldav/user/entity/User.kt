package adls.demo_caldav.user.entity

import adls.demo_caldav.core.entity.BaseEntity
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id
    var id: Long? = null,
    var email: String? = null
): BaseEntity()
