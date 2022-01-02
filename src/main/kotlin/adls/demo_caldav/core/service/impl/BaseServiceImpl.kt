package adls.demo_caldav.core.service.impl

import adls.demo_caldav.core.entity.BaseEntity
import adls.demo_caldav.core.repository.BaseRepository
import adls.demo_caldav.core.service.BaseService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.io.Serializable
import java.util.*
import javax.persistence.EntityNotFoundException

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
abstract class BaseServiceImpl<ID : Serializable, E : BaseEntity<ID>, R : BaseRepository<E, ID>> :
    BaseService<ID, E, R> {

    @Autowired
    lateinit var repository: R

    protected val log = LoggerFactory.getLogger(javaClass)

    override fun findAll(): MutableIterable<E> = repository.findAll()
    override fun findAllPageable(pageable: Pageable): Page<E> = repository.findAll(pageable)
    override fun findById(id: ID): Optional<E> = repository.findById(id)
    override fun findByIds(ids: List<ID>): List<E> = repository.findAllByIdIn(ids)
    @Throws(EntityNotFoundException::class)
    override fun findByIdOrThrow(id: ID): E =
        findById(id).orElseThrow { EntityNotFoundException("Entity $id not found.") }

    override fun save(entity: E): E = repository.save(entity)
    override fun saveAll(entities: Iterable<E>): Iterable<E> = repository.saveAll(entities)
}
