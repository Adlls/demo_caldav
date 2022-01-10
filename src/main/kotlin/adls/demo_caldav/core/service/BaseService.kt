package adls.demo_caldav.core.service

import adls.demo_caldav.core.entity.BaseEntity
import adls.demo_caldav.core.repository.BaseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.io.Serializable
import java.util.*
import javax.persistence.EntityNotFoundException

interface BaseService<ID : Serializable, E : BaseEntity, R : BaseRepository<E, ID>> {
    fun findAll(): MutableIterable<E>
    fun findAllPageable(pageable: Pageable): Page<E>
    fun findById(id: ID): Optional<E>
    fun findByIds(ids: List<ID>): List<E>
    @Throws(EntityNotFoundException::class)
    fun findByIdOrThrow(id: ID): E
    fun save(entity: E): E
    fun saveAll(entities: Iterable<E>): Iterable<E>
}
