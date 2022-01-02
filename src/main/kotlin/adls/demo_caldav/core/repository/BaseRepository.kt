package adls.demo_caldav.core.repository

import adls.demo_caldav.core.entity.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

@NoRepositoryBean
interface BaseRepository<E : BaseEntity<ID>, ID : Serializable> : JpaRepository<E, ID> {
    fun findAllByIdIn(ids: Iterable<ID>): List<E>
}
