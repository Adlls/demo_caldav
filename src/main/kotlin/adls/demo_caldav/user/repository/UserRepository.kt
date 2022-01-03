package adls.demo_caldav.user.repository

import adls.demo_caldav.core.repository.BaseRepository
import adls.demo_caldav.user.entity.User

interface UserRepository: BaseRepository<User, Long>
