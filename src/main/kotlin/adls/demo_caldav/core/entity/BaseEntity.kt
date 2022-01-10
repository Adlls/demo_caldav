package adls.demo_caldav.core.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.ZonedDateTime
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class BaseEntity {
    @UpdateTimestamp
    var updatedAt: ZonedDateTime? = ZonedDateTime.now()

    @CreationTimestamp
    var createdAt: ZonedDateTime? = ZonedDateTime.now()
}
